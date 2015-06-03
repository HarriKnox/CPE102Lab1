import java.lang.Iterable;
import java.util.Iterator;

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class TakeIterator<T> implements Iterator<T>, Iterable<T>
{
	private int remaining;
	private Iterator<T> iter;
	
	public TakeIterator(int numToTake, Iterator<T> itera)
	{
		this.remaining = numToTake;
		this.iter = itera;
	}
	
	public T next()
	{
		if (!this.hasNext()) throw new NoSuchElementException();
		this.remaining--;
		return this.iter.next();
	}
	
	public boolean hasNext()
	{
		return this.remaining > 0 && this.iter.hasNext();
	}
	
	public Iterator<T> iterator()
	{
		return this;
	}
	
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] args)
	{
		for (Integer i : new TakeIterator<Integer>(10, new UnboundedIntegerRangeIterator(1)))
		{
			System.out.println(i);
		}
	}
}