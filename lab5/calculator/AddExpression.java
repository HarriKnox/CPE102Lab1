public class AddExpression extends BinaryExpression
{
   public AddExpression(Expression lft, Expression rht)
   {
      super(lft, rht, "+");
   }

   protected double _applyOperator(double left, double right)
   {
      return left + right;
   }
}
