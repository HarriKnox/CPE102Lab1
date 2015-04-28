import java.util.Arrays;

public class Check
{
   public static void check(double result, double expected)
   {
      isError(checkit(result, expected), Double.toString(result),
         Double.toString(expected));
   }

   public static void check(int result, int expected)
   {
      isError(checkit(result, expected), Integer.toString(result),
         Integer.toString(expected));
   }

   public static void check(boolean result, boolean expected)
   {
      isError(checkit(result, expected), Boolean.toString(result),
         Boolean.toString(expected));
   }

   public static void check(char result, char expected)
   {
      isError(checkit(result, expected), "'" + result + "'",
         "'" + expected + "'");
   }

   public static void check(Object result, Object expected)
   {
      isError(checkit(result, expected), "\"" + result + "\"",
         "\"" + expected + "\"");
   }

   public static void check(int [] result, int [] expected)
   {
      if (result == null || expected == null ||
         result.length != expected.length)
      {
         isError(false, Arrays.toString(result), Arrays.toString(expected));
      }
      else
      {
         for (int i = 0; i < result.length; i++)
         {
            isError(checkit(result[i], expected[i]),
               Arrays.toString(result) + " (at index " + i + ")",
               Arrays.toString(expected));
         }
      }
   }
   public static void check(double [] result, double [] expected)
   {
      if (result == null || expected == null ||
         result.length != expected.length)
      {
         isError(false, Arrays.toString(result), Arrays.toString(expected));
      }
      else
      {
         for (int i = 0; i < result.length; i++)
         {
            isError(checkit(result[i], expected[i]),
               Arrays.toString(result) + " (at index " + i + ")",
               Arrays.toString(expected));
         }
      }
   }
   public static void check(boolean [] result, boolean [] expected)
   {
      if (result == null || expected == null ||
         result.length != expected.length)
      {
         isError(false, Arrays.toString(result), Arrays.toString(expected));
      }
      else
      {
         for (int i = 0; i < result.length; i++)
         {
            isError(checkit(result[i], expected[i]),
               Arrays.toString(result) + " (at index " + i + ")",
               Arrays.toString(expected));
         }
      }
   }
   public static void check(char [] result, char [] expected)
   {
      if (result == null || expected == null ||
         result.length != expected.length)
      {
         isError(false, Arrays.toString(result), Arrays.toString(expected));
      }
      else
      {
         for (int i = 0; i < result.length; i++)
         {
            isError(checkit(result[i], expected[i]),
               Arrays.toString(result) + " (at index " + i + ")",
               Arrays.toString(expected));
         }
      }
   }
   public static void check(Object [] result, Object [] expected)
   {
      if (result == null || expected == null ||
         result.length != expected.length)
      {
         isError(false, Arrays.toString(result), Arrays.toString(expected));
      }
      else
      {
         for (int i = 0; i < result.length; i++)
         {
            isError(checkit(result[i], expected[i]),
               Arrays.toString(result) + " (at index " + i + ")",
               Arrays.toString(expected));
         }
      }
   }

   private static boolean checkit(double result, double expected)
   {
      double epsilon = 0.0001;
      return (result < expected + epsilon) && (result > expected - epsilon);
   }

   private static boolean checkit(int result, int expected)
   {
      return result == expected;
   }

   private static boolean checkit(boolean result, boolean expected)
   {
      return result == expected;
   }

   private static boolean checkit(char result, char expected)
   {
      return result == expected;
   }

   private static boolean checkit(Object result, Object expected)
   {
      return result != null && expected != null && result.equals(expected);
   }

   private static void isError(boolean match, String result, String expected)
   {
      if (!match)
      {
         System.err.println(errorMessage(result, expected));
      }
   }

   private static int getLineNumber()
   {
      StackTraceElement[] trace = (new Throwable()).getStackTrace();
      if (trace.length > 4)
      {
         return trace[4].getLineNumber();
      }
      else
      {
         return -1;
      }
   }

   private static String errorMessage(String result, String expected)
   {
      int line = getLineNumber();
      return "Test failed on line " + line + ".  Expected " +
         expected + ", result was " + result;
   }

   public static void main(String [] args)
   {
      Check.check(1, 1);
      Check.check(1, 2);
      Check.check(1.2, 1.2);
      Check.check(1.2, 1.7);
      Check.check("abc", "abc");
      Check.check("abc", "def");
      Check.check('a', 'a');
      Check.check('a', 'b');
      Check.check(new int[]{1,2}, new int[]{1, 2});
      Check.check(new int[]{1,2}, new int[]{2, 3});
      Check.check(new int[]{1,2}, new int[]{2, 3, 4});
      Check.check(new String[]{"abc","xyz"}, new String[]{"abc"});
   }
}
