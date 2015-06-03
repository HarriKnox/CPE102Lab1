public class DivideExpression extends BinaryExpression
{
   public DivideExpression(Expression lft, Expression rht)
   {
      super(lft, rht, "/");
   }

   protected double _applyOperator(double left, double right)
   {
      return left / right;
   }
}

