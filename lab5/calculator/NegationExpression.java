public class NegationExpression
   implements Expression
{
   private Expression opnd;

   public NegationExpression(Expression opnd)
   {
      this.opnd = opnd;
   }

   public String toString()
   {
      return "-" + opnd;
   }

   public double evaluate(Bindings bindings)
   {
      return -opnd.evaluate(bindings);
   }

}
