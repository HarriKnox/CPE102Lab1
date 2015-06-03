import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;

public class CartesianPoint implements Point
{
	private double xCoord;
	private double yCoord;
	
	public CartesianPoint(double x_coord, double y_coord)
	{
		this.xCoord = x_coord;
		this.yCoord = y_coord;
	}
	
	public double xCoordinate()
	{
		return this.xCoord;
	}
	
	public double yCoordinate()
	{
		return this.yCoord;
	}
	
	public double radius()
	{
		return sqrt((this.xCoord * this.xCoord) + (this.yCoord * this.yCoord));
	}
	
	public double angle()
	{
		return atan2(this.yCoord, this.xCoord);
	}
	
	public Point rotate90()
	{
		double newX = -this.yCoord;
		double newY = this.xCoord;
		
		/*
		double newAngle = this.angle() + (PI / 2.0D);
		double rad = this.radius();
		double newX = rad * cos(newAngle);
		double newY = rad * sin(newAngle); /* */
		
		return new CartesianPoint(newX, newY);
	}
}