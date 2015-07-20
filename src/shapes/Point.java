package shapes;


/**
 * Another simple Point class, this time, only with int coordinates
 * and methods.
 * 
 * @author norm krumpe
 * 
 */
public class Point
{
	// The x and y coordinate
	private int x;
	private int y;

	/**
	 * Constructs a point with specified coordinates
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs a point that is a copy of a given point
	 * 
	 * @param p the point to be copied
	 */
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	/**
	 * Gets the x-coordinate
	 * 
	 * @return the x-coordinate
	 */
	public int getXCoor() {
		return x;
	}

	/**
	 * Sets the x-coordinate
	 * 
	 * @param x the x-coordinate
	 */
	public void setXCoor(int x) {
		this.x = x;
	}

	/**
	 * Gets the y-coordinate
	 * 
	 * @return the y-coordinate
	 */
	public int getYCoor() {
		return y;
	}

	/**
	 * Sets the y-coordinate
	 * 
	 * @param y the y-coordinate
	 */
	public void setYCoor(int y) {
		this.y = y;
	}

	/**
	 * Gets a String representation of this Point
	 * 
	 * @return a STring in the form [x=45, y=23]
	 */
	@Override
	public String toString() {
		return "[x=" + getXCoor() + ", y=" + getYCoor() + "]";
	}

	/**
	 * Determines whether a point has the same coordinates of this
	 * point
	 * 
	 * @param that the point to be compared
	 * @return true if this point has the same coordinates as those of
	 *         the parameter
	 */
	public boolean equals(Point that) {
		if (that == null)
			return false;
		return this.x == that.x && this.y == that.y;
	}
	
}
