import static java.lang.Math.sqrt;

import java.lang.Iterable;
import java.util.Iterator;

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class FilterIterator<T> implements Iterator<T>, Iterable<T>
{
	public static final Filter<Integer> OddFilter = (Integer i) -> (i & 0x1) == 1;
	public static final Filter<Integer> SquareFilter = (Integer i) -> (sqrt(i) % 1) == 0;
	public static final Filter<Integer> PrimeFilter = (Integer i) ->
	{
		if (i < 2) return false;
		for (int n = 2; n <= sqrt(i); n++)
			if (i % n == 0)
				return false;
		return true;
	};
	
	private Filter<T> fil;
	private Iterator<T> iter;
	
	private T nextValue;
	private static final int LIMIT = 10000000;
	
	public FilterIterator(Filter<T> filt, Iterator<T> itera)
	{
		this.fil = filt;
		this.iter = itera;
	}
	
	private T findNext()
	{
		for (int i = 0; i < LIMIT && this.iter.hasNext(); i++)
		{
			T val = this.iter.next();
			if (this.fil.accept(val))
			{
				return val;
			}
		}
		return null;
	}
	
	public T next()
	{
		if (this.nextValue == null)
		{
			this.nextValue = this.findNext();
		}
		if (this.nextValue != null)
		{
			T val = this.nextValue;
			this.nextValue = null;
			return val;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
	
	public boolean hasNext()
	{
		if (nextValue != null) return true;
		this.nextValue = this.findNext();
		return this.nextValue != null;
	}
	
	public Iterator<T> iterator()
	{
		return this;
	}
	
	public void remove()
	{
		throw new java.lang.UnsupportedOperationException();
	}
	
	public static void main(String[] arg)
	{
		FilterIterator<Integer> filterator = new FilterIterator<Integer>(PrimeFilter, new BoundedIntegerRangeIterator(0, 20));
		for (Integer i : filterator)
			System.out.println(i);
	}
}