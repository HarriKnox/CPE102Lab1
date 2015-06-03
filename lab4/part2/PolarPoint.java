import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.PI;

public class PolarPoint implements Point
{
	private double radius;
	private double angle;
	
	public PolarPoint(double radius, double angle)
	{
		this.radius = radius;
		this.angle = angle % (2 * PI); // Modulo to get the remainder so PI/2 is the same as 5PI/2 and 9PI/2
	}
	
	public double xCoordinate()
	{
		return this.radius * cos(this.angle);
	}
	
	public double yCoordinate()
	{
		return this.radius * sin(this.angle);
	}
	
	public double radius()
	{
		return this.radius;
	}
	
	public double angle()
	{
		return this.angle;
	}
	
	public Point rotate90()
	{
		double newAngle = this.angle() + (PI / 2.0D);
		return new PolarPoint(this.radius, newAngle);
	}
}