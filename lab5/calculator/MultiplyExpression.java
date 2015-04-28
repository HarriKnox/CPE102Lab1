public class MultiplyExpression
   implements Expression
{
   private Expression lft;
   private Expression rht;

   public MultiplyExpression(Expression lft, Expression rht)
   {
      this.lft = lft;
      this.rht = rht;
   }

   public String toString()
   {
      return "(" + lft + " * " + rht + ")";
   }

   public double evaluate(Bindings bindings)
   {
      return lft.evaluate(bindings) * rht.evaluate(bindings);
   }
}

