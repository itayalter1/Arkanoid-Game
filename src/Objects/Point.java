// 206132284 Itay Alter
package Objects;
import java.util.Random;

/**
 * This class is a class that represents a point in 2D space. It has two
 * variables, x and y, which represent the coordinates of the point. The class
 * provides methods to calculate the distance between two points, check if two
 * points are equal, and generate random points within a specified range. The
 * class also has getters and setters for the x and y coordinates. Overall,
 * this class provides basic functionality for working with points in 2D
 * space.
 */
public class Point {
    private double x;
    private double y;
    static final double COMPARISON_THRESHOLD = 0.00001;
    static final int REC_UPPER_LINE = 0;
    static final int REC_BOTTOM_LINE = 1;
    static final int REC_LEFT_LINE = 2;
    static final int REC_RIGHT_LINE = 3;

    /**
     * Creates a new Objects.Point object with the specified coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between 2 points.
     *
     * @param other the other
     * @return the distance between 2 points
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Checks if the given point is equal to another point.
     *
     * @param other the point to be compared to this point.
     * @return true if the given point is equal to this point with a small
     * tolerance, false otherwise.
     */
    public boolean equals(Point other) {
        return ((Math.abs(this.x - other.x) < COMPARISON_THRESHOLD)
                && (Math.abs(this.y - other.y) < COMPARISON_THRESHOLD));
    }

    /**
     * Returns the X coordinate of this point.
     *
     * @return the X coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the Y coordinate of this point.
     *
     * @return the Y coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the X coordinate of this point.
     *
     * @param x the new x-coordinate of the point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the Y coordinate of this point.
     *
     * @param y the new y-coordinate of the point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Generates a random point with x and y values within the specified width
     * and height ranges.
     *
     * @param width  the maximum value for x
     * @param height the maximum value for y
     * @return a randomly generated Objects.Point within the specified range
     */
    public static Point randomPoint(int width, int height) {
        Random rand = new Random();
        return new Point(rand.nextDouble() * width,
                rand.nextDouble() * height);
    }

    /**
     * This method generates a random point inside a specified rectangle area.
     * The point is generated randomly within the bounds of the rectangle.
     *
     * @param lowX  the x-coordinate of the left edge of the rectangle
     * @param lowY  the y-coordinate of the bottom edge of the rectangle
     * @param highX the x-coordinate of the right edge of the rectangle
     * @param highY the y-coordinate of the top edge of the rectangle
     * @return a randomly generated Objects.Point
     */
    public static Point randomPoint(int lowX, int lowY, int highX, int highY) {
        Random rand = new Random();
        Point point = new Point(rand.nextDouble(lowX, highX),
                rand.nextDouble(lowY, highY));
        return point;
    }


    /**
     * This method generates a random point with the given radius inside a
     * specified rectangle area. The point is generated randomly within the
     * bounds of the rectangle and centered inside a circle with the given radius.
     *
     * @param lowX   the x-coordinate of the left edge of the rectangle
     * @param lowY   the y-coordinate of the bottom edge of the rectangle
     * @param highX  the x-coordinate of the right edge of the rectangle
     * @param highY  the y-coordinate of the top edge of the rectangle
     * @param radius the radius of the circle centered at the generated point
     * @return a randomly generated Objects.Point object
     */
    public static Point randomPoint(int lowX, int lowY, int highX, int highY,
                                    int radius) {
        int diameter = radius + radius;
        Random rand = new Random();
        /*
         * calculate the available width and height for the circle to fit inside
         *  the rectangle
         */
        int width = highX - lowX - diameter;
        int height = highY - lowY - diameter;
        // generate a random center point within the rectangle
        double centerX = rand.nextDouble() * width + lowX + radius;
        double centerY = rand.nextDouble() * height + lowY + radius;
        return new Point(centerX, centerY);
    }

    /**
     * Checks if a given point is inside a given rectangle.
     *
     * @param point the point to check
     * @param rect the rectangle to check against
     * @return true if the point is inside the rectangle, false otherwise
     */
    public boolean isPointInsideRectangle(Point point, Rectangle rect) {
        return rect.contains(point);
    }

    /**
     * Returns the closest point on the perimeter of the given rectangle to this
     * point. The method calculates the closest point on each of the four lines
     * that make up the rectangle's perimeter, and returns the point that is
     * closest to this point.
     *
     * @param rec the rectangle to find the closest point on its perimeter.
     * @return the closest point on the perimeter of the given rectangle to this
     * point.
     */
    public  Point getClosestPointOnPerimeter(Rectangle rec) {
        // Create an array of the points that make up the perimeter of the rectangle
        Point upperLine = new Point(this.getX(), rec.getRecLines()[REC_UPPER_LINE].
                end().getY());
        Point bottomLine = new Point(this.getX(), rec.getRecLines()[REC_BOTTOM_LINE].
                end().getY());
        Point leftLine = new Point(rec.getRecLines()[REC_LEFT_LINE].end().getX(),
                this.getY());
        Point right = new Point(rec.getRecLines()[REC_RIGHT_LINE].end().getX(),
                this.getY());
        Point[] line = {upperLine, bottomLine, leftLine, right};
        // Find the closest point on the perimeter of the rectangle to this point
        double minDistance = this.distance(line[0]);
        int closestPointIndex = 0;
        for (int i = 0; i < line.length; i++) {
            if (minDistance > this.distance(line[i])) {
                minDistance = this.distance(line[i]);
                closestPointIndex = i;
            }
        }
        Point closestPoint = line[closestPointIndex];
        // return the closest point
        return closestPoint;
    }

}
