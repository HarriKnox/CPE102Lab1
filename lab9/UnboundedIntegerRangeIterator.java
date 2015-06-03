import java.lang.Iterable;
import java.util.Iterator;

import java.lang.UnsupportedOperationException;

public class UnboundedIntegerRangeIterator implements Iterator<Integer>, Iterable<Integer>
{
	private int current;
	
	public UnboundedIntegerRangeIterator(int low)
	{
		this.current = low;
	}
	
	public Integer next()
	{
		return this.current++;
	}
	
	public boolean hasNext()
	{
		return true;
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
		for (Integer i : new UnboundedIntegerRangeIterator(17))
		{
			if (i >= 100) break;
			System.out.print(i);
			System.out.print(' ');
		}
		
		System.out.println("");
		
		Iterator<Integer> iter = new UnboundedIntegerRangeIterator(0);
		for (; iter.next() < 100;);
		System.out.println(iter.next());
	}
}