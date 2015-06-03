import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;

import static java.lang.Math.sqrt;

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class TestCases
{
	@Test
	public void testBoundedNext()
	{
		BoundedIntegerRangeIterator bounded = new BoundedIntegerRangeIterator(0, 10);
		for (int i = 0; i < 9; i++)
			assertEquals(Integer.valueOf(i), bounded.next());
		assertEquals(Integer.valueOf(9), bounded.next());
	}
	
	@Test
	public void testBoundedIterator()
	{
		BoundedIntegerRangeIterator bounded = new BoundedIntegerRangeIterator(0, 10);
		String output = "";
		for (Integer i : bounded)
			output += i;
		assertEquals("0123456789", output);
	}
	
	@Test
	public void testBoundedHasNext()
	{
		BoundedIntegerRangeIterator bounded = new BoundedIntegerRangeIterator(0, 10);
		for (int i = 0; i < 9; i++)
			bounded.next();
		assertTrue(bounded.hasNext());
		bounded.next();
		assertFalse(bounded.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testBoundedOutOfBounds()
	{
		BoundedIntegerRangeIterator bounded = new BoundedIntegerRangeIterator(0, 10);
		for (Integer i : bounded);
		bounded.next();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testBoundedRemove()
	{
		BoundedIntegerRangeIterator bounded = new BoundedIntegerRangeIterator(0, 10);
		bounded.remove();
	}
	
	
	
	
	@Test
	public void testUnboundedNext()
	{
		UnboundedIntegerRangeIterator unbounded = new UnboundedIntegerRangeIterator(0);
		for (int i = 0; i < 9; i++)
			assertEquals(Integer.valueOf(i), unbounded.next());
		assertEquals(Integer.valueOf(9), unbounded.next());
	}
	
	@Test
	public void testUnboundedIterator()
	{
		UnboundedIntegerRangeIterator unbounded = new UnboundedIntegerRangeIterator(0);
		String output = "";
		int count = 0;
		for (Integer i : unbounded)
		{
			if (count < 20)
			{
				output += i;
				count++;
			}
			else
			{
				break;
			}
		}
		assertEquals("012345678910111213141516171819", output);
	}
	
	@Test
	public void testUnboundedHasNext()
	{
		UnboundedIntegerRangeIterator unbounded = new UnboundedIntegerRangeIterator(0);
		for (int i = 0; i < 100; i++)
			unbounded.next();
		assertTrue(unbounded.hasNext());
		unbounded.next();
		assertTrue(unbounded.hasNext());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testUnboundedRemove()
	{
		UnboundedIntegerRangeIterator unbounded = new UnboundedIntegerRangeIterator(0);
		unbounded.remove();
	}
	
	
	
	@Test
	public void testTakeBoundedNext()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(5, new BoundedIntegerRangeIterator(0, 10));
		for (int i = 0; i < 4; i++)
			assertEquals(Integer.valueOf(i), taker.next());
		assertEquals(Integer.valueOf(4), taker.next());
	}
	
	@Test
	public void testTakeUnboundedNext()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(10, new UnboundedIntegerRangeIterator(5));
		for (int i = 5; i < 14; i++)
			assertEquals(Integer.valueOf(i), taker.next());
		assertEquals(Integer.valueOf(14), taker.next());
	}
	
	@Test
	public void testTakeUnderBoundedIterator()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(10, new BoundedIntegerRangeIterator(0, 5));
		String output = "";
		for (Integer i : taker)
			output += i;
		assertEquals("01234", output);
	}
	
	@Test
	public void testTakeOverBoundedIterator()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(5, new BoundedIntegerRangeIterator(0, 10));
		String output = "";
		for (Integer i : taker)
			output += i;
		assertEquals("01234", output);
	}
	
	@Test
	public void testTakeHasNext()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(10, new UnboundedIntegerRangeIterator(0));
		for (int i = 0; i < 9; i++)
			taker.next();
		assertTrue(taker.hasNext());
		taker.next();
		assertFalse(taker.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testTakeOutOfBoundsUnderBounded()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(10, new BoundedIntegerRangeIterator(0, 5));
		for (Integer i : taker);
		taker.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testTakeOutOfBoundsOverBounded()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(5, new BoundedIntegerRangeIterator(0, 10));
		for (Integer i : taker);
		taker.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testTakeOutOfBoundsUnBounded()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(10, new UnboundedIntegerRangeIterator(0));
		for (Integer i : taker);
		taker.next();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testTakeRemove()
	{
		TakeIterator<Integer> taker = new TakeIterator<Integer>(10, new BoundedIntegerRangeIterator(0, 10));
		taker.remove();
	}
	
	
	
	
	
	@Test
	public void testFilterNextOdd()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.OddFilter, new BoundedIntegerRangeIterator(0, 10));
		for (int i = 1; i <= 7; i += 2)
			assertEquals(Integer.valueOf(i), filterer.next());
		assertEquals(Integer.valueOf(9), filterer.next());
	}
	
	@Test
	public void testFilterNextSquare()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.SquareFilter, new BoundedIntegerRangeIterator(0, 101));
		for (int i = 0; i < 10; i++)
			assertEquals(Integer.valueOf(i * i), filterer.next());
		assertEquals(Integer.valueOf(100), filterer.next());
	}
	
	@Test
	public void testFilterNextPrime()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.PrimeFilter, new BoundedIntegerRangeIterator(0, 20));
		assertEquals(Integer.valueOf(2), filterer.next());
		assertEquals(Integer.valueOf(3), filterer.next());
		assertEquals(Integer.valueOf(5), filterer.next());
		assertEquals(Integer.valueOf(7), filterer.next());
		assertEquals(Integer.valueOf(11), filterer.next());
		assertEquals(Integer.valueOf(13), filterer.next());
		assertEquals(Integer.valueOf(17), filterer.next());
		assertEquals(Integer.valueOf(19), filterer.next());
	}
	
	@Test
	public void testFilterIterator()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.SquareFilter, new BoundedIntegerRangeIterator(0, 20));
		String output = "";
		for (Integer i : filterer)
			output += i;
		assertEquals("014916", output);
	}
	
	@Test
	public void testFilterHasNextBounded()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.OddFilter, new BoundedIntegerRangeIterator(0, 10));
		for (int i = 1; i <= 7; i += 2)
			filterer.next();
		assertTrue(filterer.hasNext());
		filterer.next();
		assertFalse(filterer.hasNext());
	}
	
	@Test
	public void testFiltererHasNextUnbounded()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.SquareFilter, new UnboundedIntegerRangeIterator(0));
		for (int i = 0; i < 100; i++)
			filterer.next();
		assertTrue(filterer.hasNext());
		filterer.next();
		assertTrue(filterer.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testFiltererOutOfBounds()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.SquareFilter, new BoundedIntegerRangeIterator(0, 10));
		for (Integer i : filterer);
		filterer.next();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testFiltererRemove()
	{
		FilterIterator<Integer> filterer = new FilterIterator<Integer>(FilterIterator.PrimeFilter, new BoundedIntegerRangeIterator(0, 10));
		filterer.remove();
	}
}