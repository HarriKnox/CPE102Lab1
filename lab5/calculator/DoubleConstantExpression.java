public class DoubleConstantExpression
   implements Expression
{
   private double val;
   public DoubleConstantExpression(double val)
   {
      this.val = val;
   }

   public String toString()
   {
      return String.valueOf(val);
   }

   public double evaluate(Bindings bindings)
   {
      return val;
   }
}
