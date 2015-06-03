import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestPoints
{
	private static final double DELTA = 0.00001;
	
	@Test
	public void testCartesianCoords()
	{
		Point cartesian = new CartesianPoint(7, -12);
		assertEquals(  7, cartesian.xCoordinate(), DELTA);
		assertEquals(-12, cartesian.yCoordinate(), DELTA);
	}
	
	@Test
	public void testPolarCoords()
	{
		Point polar = new PolarPoint(9, 5);
		assertEquals( 2.5529596691690, polar.xCoordinate(), DELTA);
		assertEquals(-8.6303184719682, polar.yCoordinate(), DELTA);
	}
	
	@Test
	public void testCartesianAngles()
	{
		Point cartesian = new CartesianPoint(6, 8);
		assertEquals(10,                cartesian.radius(), DELTA);
		assertEquals( 0.92729521800161, cartesian.angle(),  DELTA);
	}
	
	@Test
	public void testPolarAngles()
	{
		Point polar = new PolarPoint(18, -96725);
		assertEquals(18,               polar.radius(), DELTA);
		assertEquals(-1.6453812774495, polar.angle(),  DELTA);
	}
	
	@Test
	public void testCartesianRotate()
	{
		Point cartesian = new CartesianPoint(-10, -8);
		Point cartRotated = cartesian.rotate90();
		assertEquals(  8, cartRotated.xCoordinate(), DELTA);
		assertEquals(-10, cartRotated.yCoordinate(), DELTA);
		assertEquals(12.806248474866,   cartRotated.radius(), DELTA);
		assertEquals(-0.89605538457134, cartRotated.angle(),  DELTA);
	}
	
	@Test
	public void testPolarRotate()
	{
		Point polar = new PolarPoint(1, 11);
		Point polarRotated = polar.rotate90();
		assertEquals(0.9999902065507, polarRotated.xCoordinate(), DELTA);
		assertEquals(0.0044256979880512, polarRotated.yCoordinate(), DELTA);
		assertEquals(1, polarRotated.radius(), DELTA);
		assertEquals(0.0044257124357241, polarRotated.angle(), DELTA);
	}
}