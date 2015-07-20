package shapes;
import java.awt.Color;



/**
 * A class that represents an isosceles right triangle with a bounding
 * box and color.
 * @author Norm Krumpe
 *
 */

public class Triangle extends Shape
{
	/**
	 * Constructs a Triangle with a given bounding box and color.
	 * @param x x-coordinate of the bounding box' upper-left corner
	 * @param y w-coordinate of the bounding box' upper-left corner
	 * @param width width of the bounding box
	 * @param height height of the bounding box
	 * @param color color of the shape
	 */
	public Triangle(int xPos, int yPos, int newWidth, int newHeight,
			Color newColor) {
		super(xPos, yPos, newWidth, newHeight, newColor);
	}

	/**
	 * Creates a triangle that is a copy of this triangle, copying its color
	 * and all the details of its bounding box.
	 *
	 * @param t the triangle that is to be copied
	 */
	public Triangle(Triangle t) {
		super(t);
	}

	/* Returns true if the parameter is the same type of shape,
	 * and everything else matches from the parent class check
	 * @see Shape#equals(java.lang.Object)
	 */
	public boolean equals(Object that) {
		if (that instanceof Triangle) {
			return super.equals(that);
		}
		return false;
	}

	/**
	 * Gets an array of points representing the vertices of this shape.
	 * @return an array of points representing the vertices of this shape.
	 */
	public Point[] getVertices() {
		Point[] result = new Point[3];

		result[0] = new Point(getX() + getWidth(), getY());
		result[1] = new Point(getX(), getY()
				+ getHeight());
		result[2] = new Point(getX() + getWidth(),
				getY() + getHeight());

		return result;
	}



}
