import java.lang.Iterable;
import java.util.Iterator;

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class BoundedIntegerRangeIterator implements Iterator<Integer>, Iterable<Integer>
{
	private int current;
	private int upper;
	
	public BoundedIntegerRangeIterator(int low, int up)
	{
		this.current = low;
		this.upper = up;
	}
	
	public Integer next()
	{
		if (!this.hasNext()) throw new NoSuchElementException();
		return this.current++;
	}
	
	public boolean hasNext()
	{
		return this.current < this.upper;
	}
	
	public Iterator<Integer> iterator()
	{
		return this;
	}
	
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] arg)
	{
		BoundedIntegerRangeIterator bounded = new BoundedIntegerRangeIterator(0, 10);
		for (Integer i : bounded)
		{
			//System.out.print(i);
			//System.out.print(' ');
		}
		System.out.println(bounded.next());
	}
}