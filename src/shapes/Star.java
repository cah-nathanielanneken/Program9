package shapes;
import java.awt.Color;



/**
 * A class that represents a concave 4-pointed "star" with a bounding
 * box and color.
 * @author Norm Krumpe
 *
 */
public class Star extends Shape
{
	/**
	 * Constructs a Star with a given bounding box and color.
	 * @param x x-coordinate of the bounding box' upper-left corner
	 * @param y w-coordinate of the bounding box' upper-left corner
	 * @param width width of the bounding box
	 * @param height height of the bounding box
	 * @param color color of the shape
	 */
	public Star(int xPos, int yPos, int newWidth, int newHeight,
			Color newColor) {
		super(xPos, yPos, newWidth, newHeight, newColor);
	}

	/**
	 * Creates a star that is a copy of this star, copying its color
	 * and all the details of its bounding box.
	 *
	 * @param t the star that is to be copied
	 */
	public Star(Star t) {
		super(t);
	}

	/* Returns true if the parameter is the same type of shape,
	 * and everything else matches from the parent class check
	 * @see Shape#equals(java.lang.Object)
	 */
	public boolean equals(Object that) {
		if (that instanceof Star) {
			return super.equals(that);
		}
		return false;
	}

	/**
	 * Gets an array of points representing the vertices of this shape.
	 * @return an array of points representing the vertices of this shape.
	 */
	public Point[] getVertices() {
		Point[] result = new Point[8];

		int x = getX();
		int y = getY();
		int h = getHeight();
		int w = getWidth();

		result[0] = new Point(x, y + h/2);
		result[1] = new Point(x + 2 * w/5, y + 2*h/5);
		result[2] = new Point(x + w/2, y);
		result[3] = new Point(x + 3*w/5, y + 2*h/5);
		result[4] = new Point(x+w, y+h/2);
		result[5] = new Point(x + 3*w/5, y+3*h/5);
		result[6] = new Point(x+w/2, y+h);
		result[7] = new Point(x + 2*w/5, y+3*h/5);

		return result;
	}


}
