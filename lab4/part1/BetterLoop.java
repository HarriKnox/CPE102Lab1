public class BetterLoop
{
   public static boolean contains(int [] values, int v)
   {
      for (int value : values)
      {
         if (v == value)
         {
            return true;
         }
      }

      return false;
   }
}
