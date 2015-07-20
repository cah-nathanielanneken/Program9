package shapes;
import java.awt.Color;


/**
 * A class that represents rectangle with a bounding
 * box and color.
 * @author Norm Krumpe
 *
 */

public class Rectangle extends Shape
{
	/**
	 * Constructs a Rectangle with a given bounding box and color.
	 * @param x x-coordinate of the bounding box' upper-left corner
	 * @param y w-coordinate of the bounding box' upper-left corner
	 * @param width width of the bounding box
	 * @param height height of the bounding box
	 * @param color color of the shape
	 */
	public Rectangle(int xPos, int yPos, int newWidth, int newHeight,
			Color newColor) {
		super(xPos, yPos, newWidth, newHeight, newColor);
	}

	/**
	 * Creates a rectangle that is a copy of this rectangle, copying its color
	 * and all the details of its bounding box.
	 *
	 * @param t the rectangle that is to be copied
	 */
	public Rectangle(Rectangle t) {
		super(t);
	}

	/* Returns true if the parameter is the same type of shape,
	 * and everything else matches from the parent class check
	 * @see Shape#equals(java.lang.Object)
	 */
	public boolean equals(Object that) {
		if (that instanceof Rectangle) {
			return super.equals(that);
		}
		return false;
	}

	/**
	 * Gets an array of points representing the vertices of this shape.
	 * @return an array of points representing the vertices of this shape.
	 */
	public Point[] getVertices() {
		Point[] result = new Point[4];

		result[0] = new Point(getX(), getY());
		result[1] = new Point(getX() + getWidth(),
				getY());
		result[2] = new Point(getX() + getWidth(),
				getY() + getHeight());
		result[3] = new Point(getX(), getY()
				+ getHeight());

		return result;
	}


}
