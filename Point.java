/**
 * Author: Nareg Amirian Megan
 * Title: Graph.java
 * Source Files: Point.java
 * Purpose: Provide a simple graph functionality to graph error
 */

public class Point {
	
	private double x, y;
	
	/**
	 * Construct point with Cartesian coordinates
	 * 
	 * @param X
	 * 			X point
	 * @param Y
	 * 			Y point
	 */
	public Point(double X, double Y) {
		x = X;
		y = Y;
	}
	
	/**
	 * Get X point
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Get Y point
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

}
