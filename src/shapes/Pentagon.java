package shapes;
import java.awt.Color;



/**
 * A class that represents a Pentagon with a bounding
 * box and color.
 * @author Norm Krumpe
 *
 */
public class Pentagon extends Shape
{
	/**
	 * Constructs a Pentagon with a given bounding box and color.
	 * @param x x-coordinate of the bounding box' upper-left corner
	 * @param y w-coordinate of the bounding box' upper-left corner
	 * @param width width of the bounding box
	 * @param height height of the bounding box
	 * @param color color of the shape
	 */
	public Pentagon(int xPos, int yPos, int newWidth, int newHeight,
			Color newColor) {
		super(xPos, yPos, newWidth, newHeight, newColor);
	}

	/**
	 * Creates a Pentagon that is a copy of this Pentagon, copying its color
	 * and all the details of its bounding box.
	 *
	 * @param t the Pentagon that is to be copied
	 */
	public Pentagon(Pentagon t) {
		super(t);
	}

	/* Returns true if the parameter is the same type of shape,
	 * and everything else matches from the parent class check
	 * @see Shape#equals(java.lang.Object)
	 */
	public boolean equals(Object that) {
		if (that instanceof Pentagon) {
			return super.equals(that);
		}
		return false;
	}

	/**
	 * Gets an array of points representing the vertices of this shape.
	 * @return an array of points representing the vertices of this shape.
	 */
	public Point[] getVertices() {
		Point[] result = new Point[5];

		int x = getX();
		int y = getY();
		int width = getWidth();
		int height = getHeight();

		result[0] = new Point(x + width/2, y);
		result[1] = new Point(x + width, y + height / 3);
		result[2] = new Point(x + width - width/4, y + height);
		result[3] = new Point(x + width/4, y + height);
		result[4] = new Point(x, y + height/3);

		return result;
	}

}
