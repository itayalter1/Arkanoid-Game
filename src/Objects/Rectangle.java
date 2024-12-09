// 206132284 Itay Alter
package Objects;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a rectangle on a 2D plane, defined by an upper-left
 * corner point, a width, and a height. The class provides methods to calculate
 * the intersection points between the rectangle and a given line, to divide the
 * rectangle into five equal regions, to check if a ball or a point is contained
 * within the rectangle, and to get and set its properties. This class
 * also has a static method to create a new rectangle with a random upper-left
 * corner point and given width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] recLines;
    static final int REGIONS_NUMBER = 5;
    static final int REC_UPPER_LINE = 0;
    static final int REC_BOTTOM_LINE = 1;
    static final int REC_LEFT_LINE = 2;
    static final int REC_RIGHT_LINE = 3;
    static final int REC_LINE_NUMBER = 4;

    /**
     * Constructs a new rectangle with the specified upper-left point, width,
     * and height. recLinesArray is a private helper method used to create an
     * array of the four sides of the rectangle based on its upper-left point,
     * width, and height. Each side is represented by a Objects.Line object.
     *
     * @param upperLeft the upper-left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.recLines = recLinesArray(upperLeft, width, height);
    }

    /**
     * This method takes the upper left corner of a rectangle, its width, and
     * its height as parameters and returns an array of four lines that
     * represent the edges of the rectangle. It calculates the coordinates of
     * the other three corners of the rectangle using the width and height, and
     * then creates a line for each edge of the rectangle using the corner
     * points. Finally, it returns the array of four lines.
     *
     * @param upperLeft the upper left corner of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     * @return an array of four lines representing the edges of the rectangle.
     */
    public Line[] recLinesArray(Point upperLeft, double width, double height) {
        // Define the other three corners of the rectangle.
        Point upperRight = new Point(upperLeft.getX() + width,
                upperLeft.getY());
        Point bottomRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
        Point bottomLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + height);
        // Create an array of four lines, each representing the rectangle.
        Line[] lines = new Line[REC_LINE_NUMBER];
        lines[REC_UPPER_LINE] = new Line(upperLeft, upperRight);
        lines[REC_BOTTOM_LINE] = new Line(bottomLeft, bottomRight);
        lines[REC_LEFT_LINE] = new Line(upperLeft, bottomLeft);
        lines[REC_RIGHT_LINE] = new Line(upperRight, bottomRight);
        return lines;
    }

    /**
     * This method takes a line as input and checks if it intersects with any of
     * the four lines that make up the current rectangle. It does this by
     * iterating through each line of the rectangle and using the
     * intersectionWith method  to check for intersection with the given line.
     * If an intersection point is found, it is added to an ArrayList of
     * intersection points. If no intersection points were found, the method
     * returns null. Otherwise, it returns the list of intersection points.
     *
     * @param line the line to check for intersection with the rectangle.
     * @return a list of intersection points between the rectangle and the line.
     * If there are no intersections, null is returned.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersectionPoints = new ArrayList<>();
        /*
         *Check for intersection between the given line and each line of the
         *  rectangle.
         */
        for (Line line1 : this.recLines) {
            Point intersection = line1.intersectionWith(line);
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        return intersectionPoints;
    }

    /**
     * Returns the width of the triangle.
     *
     * @return the width of the triangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the triangle.
     *
     * @return the height of the triangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper left point of the triangle.
     *
     * @return the upper left point of the triangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the 4 lines that defines the triangle.
     *
     * @return the array of the 4 lines that defines the triangle.
     */
    public Line[] getRecLines() {
        return this.recLines;
    }

    /**
     * Sets the lines that represent the rectangle according to a given
     * rectangle.
     *
     * @param rectangle the rectangle whose lines will be set
     */
    public void setRecLines(Rectangle rectangle) {
        this.recLines = this.recLinesArray(rectangle.getUpperLeft(),
                rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * Sets the height of the rectangle to the given value.
     *
     * @param height the new height of the rectangle
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Sets the width of the rectangle to the given value.
     *
     * @param width the new width of the rectangle
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets the upper left point of the rectangle to the given value.
     *
     * @param upperLeft the new upper left point of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Returns a new rectangle with a random width and height, and a fixed
     * upper-left point.
     *
     * @param width  the width of the new rectangle.
     * @param height the height of the new rectangle.
     * @param x      the x coordinate of the new rectangle upper left point.
     * @param y      the y coordinate of the new rectangle upper left point.
     * @return a new rectangle object with random width and height.
     */
    public static Rectangle newRec(int width, int height, double x, double y) {
        Random random = new Random();
        Point upperLeft = new Point(x, y);
        return new Rectangle(upperLeft, width, height);
    }

    /**
     * Divides the rectangle into five equal horizontal regions and returns an
     * array of lines that represent these regions. The upper line of the
     * rectangle is used to calculate the start and end points of the dividing
     * lines.
     *
     * @return an array of five lines representing the five regions.
     */
    public Line[] separateRegions() {
        Line[] regions = new Line[REGIONS_NUMBER];
        double lineStart = this.recLines[REC_UPPER_LINE].start().getX();
        double lineEnd = this.recLines[REC_UPPER_LINE].end().getX();
        // divide the rectangle into five regions and create a line for each
        for (int i = 0; i < REGIONS_NUMBER; i++) {
            Point start = new Point((lineStart
                    + (i * ((lineEnd - lineStart) / REGIONS_NUMBER))),
                    this.recLines[REC_UPPER_LINE].start().getY());
            Point end = new Point((lineEnd
                    - ((REGIONS_NUMBER - i - 1) * ((lineEnd - lineStart)
                    / REGIONS_NUMBER))),
                    this.recLines[REC_UPPER_LINE].start().getY());
            regions[i] = new Line(start, end);
        }
        return regions;
    }


    /**
     * Checks if the given point is contained within the bounds of this rectangle.
     *
     * @param p the point to check
     * @return true if the point is inside the rectangle, false otherwise.
     */
    public boolean contains(Point p) {
        double x = p.getX();
        double y = p.getY();
        double rX = this.upperLeft.getX();
        double rY = this.upperLeft.getY();

        if (x >= rX && x < rX + this.width && y >= rY && y < rY + this.height) {
            return true;
        }
        return false;
    }
}
