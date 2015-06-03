import java.util.LinkedList;

public class Point
{
	private final int x;
	private final int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof Point)
		{
			Point p = (Point)o;
			return this.x == p.getX() && this.y == p.getY();
		}
		return false;
	}
}