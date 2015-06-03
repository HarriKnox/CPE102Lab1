public class SubtractExpression extends BinaryExpression
{
   public SubtractExpression(Expression lft, Expression rht)
   {
      super(lft, rht, "-");
   }
   
   protected double _applyOperator(double left, double right)
   {
      return left - right;
   }
}

