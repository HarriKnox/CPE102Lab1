import java.util.List;
import java.util.LinkedList;

public class SimpleList
{
   public static List<Integer> squareAll(List<Integer> values)
   {
      List<Integer> newValues = new LinkedList<Integer>();
      for (Integer val : values)
      {
         newValues.add(val * val);
      }

      return newValues;
   }
}
