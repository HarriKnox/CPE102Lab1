import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.util.NoSuchElementException;

public class IterativeTestList
{
   @Test(expected=IndexOutOfBoundsException.class)
   public void testAddException()
   {
      new IterativeList<String>().add(3, "empty");
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void testAddNegException()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      list.add(-1, "negative");
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void testAddOutException()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      list.add(9, "too big");
   }

   @Test(expected=NoSuchElementException.class)
   public void testIndexException()
   {
      new IterativeList<String>().indexOf("empty");
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void testGetEmptyException()
   {
      new IterativeList<String>().get(0);
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void testGetNegException()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      list.get(-1);
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void testGetBigException()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      list.get(9);
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void testRemoveNegException()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      list.remove(-1);
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void testRemoveBigException()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      list.get(9);
   }

   @Test
   public void testInit()
   {
      assertEquals(0, new IterativeList<Integer>().size());
   }

   @Test
   public void testInit2()
   {
      assertEquals(0, new IterativeList<Integer>().map(x -> x).size());
   }

   @Test
   public void testAdd()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");

      assertEquals(4, list.size());
      assertEquals("high", list.get(0));
      assertEquals("hi", list.get(1));
      assertEquals("hello", list.get(2));
      assertEquals("lAsT string", list.get(3));

      assertEquals(list.indexOf("high"), 0);
      assertEquals(list.indexOf("hi"), 1);
      assertEquals(list.indexOf("lAsT string"), 3);

      list.add(0, "new first");

      assertEquals(5, list.size());
      assertEquals("new first", list.get(0));
      assertEquals("high", list.get(1));
      assertEquals("hi", list.get(2));
      assertEquals("hello", list.get(3));
      assertEquals("lAsT string", list.get(4));

      assertEquals(1, list.indexOf("high"));
      assertEquals(2, list.indexOf("hi"));
      assertEquals(4, list.indexOf("lAsT string"));

      list.add(2, "MiddlE");

      assertEquals(6, list.size());
      assertEquals("new first", list.get(0));
      assertEquals("high", list.get(1));
      assertEquals("MiddlE", list.get(2));
      assertEquals("hi", list.get(3));
      assertEquals("hello", list.get(4));
      assertEquals("lAsT string", list.get(5));

      assertEquals(1, list.indexOf("high"));
      assertEquals(3, list.indexOf("hi"));
      assertEquals(5, list.indexOf("lAsT string"));

      list.add(6, "actual end");

      assertEquals(7, list.size());
      assertEquals("new first", list.get(0));
      assertEquals("high", list.get(1));
      assertEquals("MiddlE", list.get(2));
      assertEquals("hi", list.get(3));
      assertEquals("hello", list.get(4));
      assertEquals("lAsT string", list.get(5));
      assertEquals("actual end", list.get(6));

      assertEquals(1, list.indexOf("high"));
      assertEquals(3, list.indexOf("hi"));
      assertEquals(5, list.indexOf("lAsT string"));
   }

   @Test
   public void testRemove()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      list.add(0, "new first");
      list.add(2, "MiddlE");
      list.add(6, "actual end");

      list.remove(0);

      assertEquals(list.size(), 6);
      assertEquals("high", list.get(0));
      assertEquals("MiddlE", list.get(1));
      assertEquals("hi", list.get(2));
      assertEquals("hello", list.get(3));
      assertEquals("lAsT string", list.get(4));
      assertEquals("actual end", list.get(5));

      assertEquals(0, list.indexOf("high"));
      assertEquals(2, list.indexOf("hi"));
      assertEquals(4, list.indexOf("lAsT string"));

      list.remove(2);

      assertEquals(5, list.size());
      assertEquals("high", list.get(0));
      assertEquals("MiddlE", list.get(1));
      assertEquals("hello", list.get(2));
      assertEquals("lAsT string", list.get(3));
      assertEquals("actual end", list.get(4));

      assertEquals(0, list.indexOf("high"));
      assertEquals(3, list.indexOf("lAsT string"));

      list.remove(4);

      assertEquals(4, list.size());
      assertEquals("high", list.get(0));
      assertEquals("MiddlE", list.get(1));
      assertEquals("hello", list.get(2));
      assertEquals("lAsT string", list.get(3));

      assertEquals(0, list.indexOf("high"));
      assertEquals(3, list.indexOf("lAsT string"));

      list.remove(0);
      list.remove(0);
      list.remove(0);
      list.remove(0);
      assertEquals(0, list.size());
   }

   @Test
   public void testMap()
   {
      SimpleList<Integer> list = new IterativeList<>();
      list.addToEnd(0);
      list.addToEnd(2);
      list.addToEnd(4);
      list.addToEnd(3);
      list.addToEnd(1);
      SimpleList<Integer> result = list.map(x -> x * x);

      assertEquals(0, (int)result.get(0));
      assertEquals(4, (int)result.get(1));
      assertEquals(16, (int)result.get(2));
      assertEquals(9, (int)result.get(3));
      assertEquals(1, (int)result.get(4));
   }

   @Test
   public void testMap2()
   {
      SimpleList<String> list = new IterativeList<>();
      list.addToEnd("high");
      list.addToEnd("hi");
      list.addToEnd("hello");
      list.addToEnd("lAsT string");
      SimpleList<Integer> result = list.map(String::length);

      assertEquals(4, (int)result.get(0));
      assertEquals(2, (int)result.get(1));
      assertEquals(5, (int)result.get(2));
      assertEquals(11, (int)result.get(3));
   }
}
