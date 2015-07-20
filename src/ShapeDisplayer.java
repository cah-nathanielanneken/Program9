import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import shapes.Hexagon;
import shapes.Pentagon;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Star;
import shapes.Triangle;

/**
 * A class that draws various shapes. You should NOT modify anything in this
 * file (but feel free to read through the code) . If you think there is a
 * problem with this code, please contact your instructor.
 * 
 * @author DJ Rao, Keith Frikken, Lukasz Opyrchal, Norm Krumpe, Nathan Anneken
 * 
 */
public class ShapeDisplayer extends JFrame {

	private JLabel mouseLocation;
	private ArrayList<Shape> shapeList;
	private JLabel results;

	public ShapeDisplayer() {
		super("Shape Display");

		shapeList = new ArrayList<Shape>();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 450));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		// Create a scroll pane to contain the components.
		JScrollPane jsp = new JScrollPane(new Board());
		add(jsp, BorderLayout.CENTER);

		// Create a JLabel to display mouse coordinates.
		mouseLocation = new JLabel("  Mouse Location: x=  " + ", y=");
		add(mouseLocation, BorderLayout.SOUTH);

		JPanel buttons = new JPanel();
		JButton save, load, exit, clear;
		ButtonListener bl = new ButtonListener();
		save = new JButton("Save");
		load = new JButton("Load");
		clear = new JButton("Clear");
		exit = new JButton("Exit");

		exit.addActionListener(bl);
		clear.addActionListener(bl);
		load.addActionListener(bl);
		save.addActionListener(bl);
		buttons.add(save);
		buttons.add(load);
		buttons.add(clear);
		buttons.add(exit);

		add(buttons, BorderLayout.NORTH);

		// Pack and show
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Adds a shape to the ShapeDisplayer. There is a better way to do this, but
	 * we need to cover polymorphism and abstract classes first. Shape must be a
	 * triangle, rectangle, or star.
	 * 
	 * @param newShape
	 *            The shape to add.
	 */
	public void addShape(Shape newShape) {
		if (newShape.isInsideBox(400, 400))
			shapeList.add(newShape);

		repaint();
	}

	/**
	 * A board class that extends JComponent, represents the grid area
	 * 
	 * @author DJ Rao, Keith Frikken
	 * 
	 */
	private class Board extends JComponent {

		/**
		 * A default constructor.
		 */
		public Board() {
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent me) {
					mouseLocation.setText("  Mouse Location: x=  " + me.getX()
							+ ", y=" + me.getY());
				}
			});

			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent me) {

					if (me.getButton() == MouseEvent.BUTTON1) {
						int width = 0, height = 0;
						while (width < 30 || height < 30) {
							width = (int) (Math.random() * 100);
							height = (int) (Math.random() * 100);
						}
						shapeList.add(randomShape(me.getX(), me.getY(), width,
								height));
						repaint();
					} else if (me.getButton() == MouseEvent.BUTTON3) {
						int x = me.getX(), y = me.getY(), xDiff = 0, yDiff = 0, distance = 400, tempDistance = 0;
						Shape a = null;
						for (Shape s : shapeList) {
							xDiff = Math.abs(s.getX() - x);
							yDiff = Math.abs(s.getY() - y);
							tempDistance = (int) Math.sqrt(Math.pow(xDiff, 2)
									+ Math.pow(yDiff, 2));
							if (tempDistance < distance) {
								distance = tempDistance;
								a = s;
							}
						}
						shapeList.remove(a);
						repaint();
					}

				}
			});
		}

		/**
		 * Sets the preferred size, but it is not explicitly called.
		 */
		public Dimension getPreferredSize() {
			Dimension size = new Dimension(200, 200);
			return size;
		}

		/**
		 * Paints all triangles in the triangle list.
		 */
		public void paintComponent(Graphics g) {
			paintBackground(g);
			for (int i = 0; (i < shapeList.size()); i++) {
				Point[] points = new Point[0];
				Shape current = shapeList.get(i);

				points = current.getVertices();

				Color shapeColor = current.getColor();
				Polygon vertices = new Polygon();
				for (Point p : points) {
					vertices.addPoint(p.getXCoor(), p.getYCoor());
				}
				g.setColor(new Color(shapeColor.getRed(),
						shapeColor.getGreen(), shapeColor.getBlue(), 200));
				g.fillPolygon(vertices);
				g.setColor(shapeColor);
				g.drawPolygon(vertices);
			}

		}

		/**
		 * Creates the background. Makes the grid and sets the backgroun to
		 * black.
		 * 
		 * @param g
		 *            A graphic component.
		 */
		public void paintBackground(Graphics g) {
			final int Width = getWidth();
			final int Height = getHeight();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			// Draw horizontal lines and print labels with small font.
			g.setFont(g.getFont().deriveFont(g.getFont().getSize() - 2.0f));
			for (int y = 0; (y < Height); y += 50) {
				g.drawLine(0, y, Width, y);
				g.drawString("" + y, 0, y - 2);
				g.drawString("" + y, Width - 25, y - 2);
			}
			// Draw vertical lines and print labels.
			for (int x = 0; (x < Width); x += 50) {
				g.drawLine(x, 0, x, Height);
				g.drawString("" + x, x + 1, 10);
				g.drawString("" + x, x + 1, Height - 1);
			}
		}
	}

	/**
	 * Returns a random shape (rectangle, triangle, hexagon, pentagon, or star)
	 * with a random color in the specified location.
	 * 
	 * @param x
	 *            The x-coordinate of the upper-left corner of the bounding
	 *            rectangle
	 * @param y
	 *            The y-coordinate of the upper-left corner of the bounding
	 *            rectangle
	 * @param width
	 *            The width of the bounding rectangle of the shape
	 * @param height
	 *            The height of the bounding rectangle of the shape
	 * @return a reference to the created shape
	 */
	public static Shape randomShape(int x, int y, int width, int height) {

		int rnd = (int) (5 * Math.random());
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());
		int blue = (int) (256 * Math.random());
		Color c = new Color(red, green, blue);
		Shape s = null;

		switch (rnd) {
		case 0:
			s = new Rectangle(x, y, width, height, c);
			break;
		case 1:
			s = new Triangle(x, y, width, height, c);
			break;
		case 2:
			s = new Hexagon(x, y, width, height, c);
			break;
		case 3:
			s = new Pentagon(x, y, width, height, c);
			break;
		case 4:
			s = new Star(x, y, width, height, c);
			break;
		}

		return s;
	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Exit")) {
				new JOptionPane();
				System.exit(0);

			} else if (ae.getActionCommand().equals("Clear")) {
				int clear = JOptionPane.showConfirmDialog(null,
						"Do you REALLY want to clear all the shapes?",
						"Warning", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (clear == JOptionPane.YES_OPTION) {
					shapeList.clear();
					repaint();
				}

			} else if (ae.getActionCommand().equals("Save")) {
				try {
					saveData();
				} catch (IOException e) {
					System.out.println("Error in saving data, IOException");
				}

			} else {
				int clear = JOptionPane.showConfirmDialog(null,
						"Do you REALLY want to clear all the shapes?",
						"Warning", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (clear == JOptionPane.YES_OPTION) {
					shapeList.clear();
					try {
						readData();
					} catch (IOException e) {
						System.out
								.println("Error in loading data, IOException");
					}
					repaint();
				}

			}

		}

	}

	private void saveData() throws IOException {
		FileOutputStream fos = new FileOutputStream("shapes.dat");
		DataOutputStream dos = new DataOutputStream(fos);

		dos.writeInt(shapeList.size());
		for (Shape a : shapeList) {

			String s = a.getClass().getName().substring(7);
			int shape = 0;
			if (s.equals("Rectangle")) {
				shape = 0;
			} else if (s.equals("Triangle")) {
				shape = 1;
			} else if (s.equals("Hexagon")) {
				shape = 2;
			} else if (s.equals("Pentagon")) {
				shape = 3;
			} else if (s.equals("Star")) {
				shape = 4;
			}

			dos.writeInt(shape);
			dos.writeInt(a.getX());
			dos.writeInt(a.getY());
			dos.writeInt(a.getWidth());
			dos.writeInt(a.getHeight());
			dos.writeInt(a.getColor().getRed());
			dos.writeInt(a.getColor().getGreen());
			dos.writeInt(a.getColor().getBlue());
		}

		dos.close();
		fos.close();
	}

	private void readData() throws IOException {
		FileInputStream fis = new FileInputStream("shapes.dat");
		DataInputStream dis = new DataInputStream(fis);

		int numberOfShapes = dis.readInt(), temp = 0;
		while (temp < numberOfShapes) {
			int shape = dis.readInt();
			int xCord = dis.readInt();
			int yCord = dis.readInt();
			int width = dis.readInt();
			int height = dis.readInt();
			int red = dis.readInt();
			int green = dis.readInt();
			int blue = dis.readInt();
			Color c = new Color(red, green, blue);

			switch (shape) {
			case 0:
				shapeList.add(new Rectangle(xCord, yCord, width, height, c));
				break;
			case 1:
				shapeList.add(new Triangle(xCord, yCord, width, height, c));
				break;
			case 2:
				shapeList.add(new Hexagon(xCord, yCord, width, height, c));
				break;
			case 3:
				shapeList.add(new Pentagon(xCord, yCord, width, height, c));
				break;
			case 4:
				shapeList.add(new Star(xCord, yCord, width, height, c));
				break;
			}
			temp++;
		}

		dis.close();
		fis.close();
	}

	public static void main(String[] args) {
		new ShapeDisplayer();
	}

}
