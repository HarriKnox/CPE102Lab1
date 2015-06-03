public class SimpleArray
{
   public static int [] squareAll(int values[])
   {
      int [] newValues = new int[values.length];

      for (int i = 0; i < values.length; i++)
      {
         newValues[i] = values[i] * values[i];
      }

      return newValues;
   }
}
