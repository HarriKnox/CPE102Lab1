public abstract class BinaryExpression implements Expression
{
	private Expression left;
	private Expression right;
	private String operator;
	
	protected BinaryExpression(Expression left, Expression right, String operator)
	{
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public String toString()
	{
		return "(" + this.left + this.operator + this.right + ")";
	}
	
	abstract protected double _applyOperator(double left, double right);
	
	public double evaluate(Bindings bindings)
	{
		return this._applyOperator(this.left.evaluate(bindings), this.right.evaluate(bindings));
	}
}