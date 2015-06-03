import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class TestCases
{
   private static final double DELTA = 0.00001;

   @Test
   public void testSimpleIf1()
   {
      assertEquals(1.7, SimpleIf.max(1.2, 1.7), DELTA);
   }

   @Test
   public void testSimpleIf2()
   {
      assertEquals(9.0, SimpleIf.max(9.0, 3.2), DELTA);
   }

   @Test
   public void testSimpleIf3()
   {
      assertEquals(-48.2, SimpleIf.max(-314.15, -48.2), DELTA);
   }

   @Test
   public void testSimpleLoop1()
   {
      assertEquals(7, SimpleLoop.sum(3, 4));
   }

   @Test
   public void testSimpleLoop2()
   {
      assertEquals(7, SimpleLoop.sum(-2, 4));
   }

   @Test
   public void testSimpleLoop3()
   {
      assertEquals(45, SimpleLoop.sum(0, 9));
   }

   @Test
   public void testSimpleArray1()
   {
      /* What are those parameters?  They are newly allocated arrays
         with initial values. */
      assertArrayEquals(null, 
         SimpleArray.squareAll(new int[] {1, 2, 3}), new int[] {1, 4, 9});
   }

   @Test
   public void testSimpleArray2()
   {
      assertArrayEquals(null, 
         SimpleArray.squareAll(new int[] {7, 5}), new int[] {49, 25});
   }

   @Test
   public void testSimpleArray3()
   {
      assertArrayEquals(null,
         SimpleArray.squareAll(new int[] {-1, -3, 5}), new int[] {1, 9, 25});
   }

   @Test
   public void testSimpleList1()
   {
      List<Integer> input =
         new LinkedList<Integer>(Arrays.asList(new Integer[] {1, 2, 3}));
      List<Integer> expected =
         new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 4, 9}));

      assertEquals(expected, SimpleList.squareAll(input));

      List<Integer> otherInput =
         new LinkedList<Integer>(Arrays.asList(new Integer[] {-13, 6, 7, -5, 3, -8}));
      List<Integer> otherExpected =
         new ArrayList<Integer>(Arrays.asList(new Integer[] {169, 36, 49, 25, 9, 64}));

      assertEquals(otherExpected, SimpleList.squareAll(otherInput));
   }

   @Test
   public void testSimpleList2()
   {
      List<Integer> input =
         new LinkedList<Integer>(Arrays.asList(new Integer[] {7, -5, 13, 2, 4, -15, -8, 15, 7, 9, -9}));
      List<Integer> expected =
         new ArrayList<Integer>(Arrays.asList(new Integer[] {49, 25, 169, 4, 16, 225, 64, 225, 49, 81, 81}));

      assertEquals(expected, SimpleList.squareAll(input));
   }

   @Test
   public void testBetterLoop1()
   {
      assertTrue(BetterLoop.contains(new int[] {7, 5}, 5));
   }

   @Test
   public void testBetterLoop2()
   {
      assertTrue(BetterLoop.contains(new int[] {7, 5, 2, 4}, 4));
   }

   @Test
   public void testBetterLoop3()
   {
      assertFalse(BetterLoop.contains(new int[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233}, 7));
   }
}
