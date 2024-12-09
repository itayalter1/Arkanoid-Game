// 206132284 Itay Alter
package Objects;

import java.util.ArrayList;

/**
 * This class represents a line in two-dimensional space. It has two constructors,
 * one that takes two points as arguments and another that takes four doubles
 * representing the coordinates of the start and end points of the line. The
 * class provides methods to calculate the length of the line, find the middle
 * point of the line, and retrieve the start and end points of the line as points.
 * Additionally, the class has a method to check if the line intersects with
 * another line.
 */
public class Line {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    static final double COMPARISON_THRESHOLD = 0.00001;
    static final int DECIMAL = 10;
    static final int HALF = 2;

    /**
     * Constructs a new line between two given points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
// constructors
    public Line(Point start, Point end) {
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Constructs a new line between two given points.
     *
     * @param x1 the X coordinate of the start point of the line
     * @param y1 the Y coordinate of the start point of the line
     * @param x2 the X coordinate of the end point of the line
     * @param y2 the Y coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Calculates the length of the line.
     *
     * @return the length of the line.
     */
// Return the length of the line
    public double length() {
        double distance = Math.sqrt(((this.x1 - this.x2) * (this.x1 - this.x2))
                + (this.y1 - this.y2) * (this.y1 - this.y2));
        return distance;
    }

    /**
     * Finds the middle point of the line.
     *
     * @return the middle point of the line.
     */
// Returns the middle point of the line
    public Point middle() {
        double x = (this.x1 + this.x2) / HALF;
        double y = (this.y1 + this.y2) / HALF;
        return new Point(x, y);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point of the line.
     */
// Returns the start point of the line
    public Point start() {
        return new Point(this.x1, this.y1);
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the ending point of the line.
     */
// Returns the end point of the line
    public Point end() {
        return new Point(this.x2, this.y2);
    }

    /**
     * This method checks whether the given line intersects with another line or
     * not. It first extracts the start and end points of both lines. Then, it
     * calculates the orientation of three ordered points and checks if the
     * intersection point is on both lines. If the orientations of these points
     * are different for both lines, then the lines are intersecting.
     * In case the four points are collinear, meaning that they are on the same
     * line, then the method checks if any of the points of the given line is on
     * the other line. If so, the method returns true, indicating that the lines
     * are intersecting.
     *
     * @param other the line to check intersection with
     * @return true if this line is intersecting with the given line, false
     * otherwise
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        // Get the four points that define the two lines
        Point p1 = this.start();
        Point p2 = this.end();
        Point p3 = other.start();
        Point p4 = other.end();

        // Calculate the orientation of three ordered points
        // Used to check if the intersection point is on both lines
        int o1 = findOrientation(p1, p2, p3);
        int o2 = findOrientation(p1, p2, p4);
        int o3 = findOrientation(p3, p4, p1);
        int o4 = findOrientation(p3, p4, p2);

        // Check if the two lines are intersecting
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special cases when the four points are collinear
        if (o1 == 0 && isOnSegment(p1, p3, p2)) {
            return true;
        }
        if (o2 == 0 && isOnSegment(p1, p4, p2)) {
            return true;
        }
        if (o3 == 0 && isOnSegment(p3, p1, p4)) {
            return true;
        }
        if (o4 == 0 && isOnSegment(p3, p2, p4)) {
            return true;
        }

        return false;
    }

    /**
     * Returns the orientation of three points. The orientation is defined as
     * follows: 0 if the points are collinear. 1 if the orientation is clockwise
     * -1 if the orientation is counterclockwise. The orientation is used to
     * check if 2 line are intersects.
     *
     * @param p1 the first point.
     * @param p2 the second point.
     * @param p3 the third point.
     * @return the orientation of three points.
     */
    private int findOrientation(Point p1, Point p2, Point p3) {
        double orientation = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX())
                - (p2.getX() - p1.getX()) * (p3.getY() - p2.getY());

        if (orientation == 0) {
            return 0;
        } else if (orientation > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * This method takes three points p1, p2 and p3, and checks if p2 lies on
     * the line segment between p1 and p3. It returns true if p2 is on the line
     * segment and false otherwise. To check this, we first find the range of x
     * and y coordinates for the line segment and then check if the x and y
     * coordinates of p2 lie within this range. If both the x and y coordinates
     * of p2 are within the range, then p2 lies
     * on the line segment.
     *
     * @param p1 the start point of the line segment
     * @param p2 the point to check if it's on the line segment
     * @param p3 the end point of the line segment
     * @return true if p2 is on the line segment, false otherwise.
     */
    private boolean isOnSegment(Point p1, Point p2, Point p3) {
        if (p2.getX() <= Math.max(p1.getX(), p3.getX())
                && p2.getX() >= Math.min(p1.getX(), p3.getX())
                && p2.getY() <= Math.max(p1.getY(), p3.getY())
                && p2.getY() >= Math.min(p1.getY(), p3.getY())) {
            return true;
        }
        return false;
    }

    /**
     * computes the intersection point between this line and another line.
     * If the two lines are intersecting, the method continues to calculate the
     * intersection point using the line equations. It returns the intersection
     * point if it exists, or null if the lines do not intersect. The method
     * uses Cramer's rule to solve the system of equations and compute the
     * values of x and y at the intersection point. It also handles the cases of
     * overlap lines and lines the share one endpoint. if the lines intersect or
     * share one endpoint , it returns this point. if the lines do not intersect
     * or the lines are overlap, it returns null.
     *
     * @param other the other line to intersect with
     * @return the point of intersection or null if the lines do not intersect
     */
    public Point intersectionWith(Line other) {
        // Check if the two lines intersect
        if (isIntersecting(other)) {
            // Get the start and end points of the other line.
            double x1 = this.start().getX();
            double y1 = this.start().getY();
            double x2 = this.end().getX();
            double y2 = this.end().getY();
            double x3 = other.start().getX();
            double y3 = other.start().getY();
            double x4 = other.end().getX();
            double y4 = other.end().getY();
            /*
             * Check for intersection between the other line and a slightly
             * shifted line in different directions
             */
            boolean result1 = isIntersecting(new Line(other.x1
                    - COMPARISON_THRESHOLD * DECIMAL, other.y1, other.x2
                    + COMPARISON_THRESHOLD * DECIMAL, other.y2));
            boolean result2 = isIntersecting(new Line(other.x1,
                    other.y1 - COMPARISON_THRESHOLD * DECIMAL, other.x2,
                    other.y2 + COMPARISON_THRESHOLD * DECIMAL));
            boolean result3 = isIntersecting(new Line(other.x2
                    - COMPARISON_THRESHOLD * DECIMAL, other.y2, other.x1
                    + COMPARISON_THRESHOLD * DECIMAL, other.y1));
            boolean result4 = isIntersecting(new Line(other.x2,
                    other.y2 - COMPARISON_THRESHOLD * DECIMAL, other.x1,
                    other.y1 + COMPARISON_THRESHOLD * DECIMAL));
            // Handle the case where the two lines are parallel
            if ((x2 - x1) * (y4 - y3) == (x4 - x3) * (y2 - y1)) {
                /*
                 * Check for special cases where the lines share an endpoint and
                 *  do not intersect anywhere else
                 */
                if ((x1 == x4 && y1 == y4) && !(result1 && result2
                        && result3 && result4)) {
                    return new Point(x1, y1);
                } else if ((x2 == x3 && y2 == y3) && !(result1 && result2
                        && result3 && result4)) {
                    return new Point(x2, y2);
                } else if ((x2 == x4 && y2 == y4) && !(result1 && result2
                        && result3 && result4)) {
                    return new Point(x2, y2);
                } else if ((x1 == x3 && y1 == y3) && !(result1 && result2
                        && result3 && result4)) {
                    return new Point(x2, y2);
                } else {
                    // The two lines are overlap
                    return null;
                }
            }
            // Calculate the intersection point
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2)
                    * (x3 * y4 - y3 * x4))
                    / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
            double y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2)
                    * (x3 * y4 - y3 * x4))
                    / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

            return new Point(x, y);
        }
        return null;
    }


    /**
     * Checks  if the given line is equal to this line. The equality is defined
     * based on the start and end points of the lines. If they are, the method
     * returns true. If both checks fail, the method returns false, indicating
     * that the given line is not equal to this line.
     *
     * @param other the line to compare to
     * @return true if the  lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if ((other.start().equals(this.start()))
                && (other.end().equals(this.end()))) {
            return true;
        }
        if ((other.start().equals(this.end()))
                && (other.end().equals(this.start()))) {
            return true;
        }
        return false;
    }

    /**
     * The method iterates over the lines of the given rectangle and checks for
     * intersection with this line. If there are no intersection points found,
     * it returns null. Otherwise, it finds all the intersection points and
     * returns the closest one to the start point of this line.
     *
     * @param rect the rectangle to check for intersections with this line
     * @return the closest intersection point to the start point of this line,
     * or null if there are no intersection points
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int counter = 0;
        // Iterate over the lines of the rectangle to check for intersections.
        for (Line line : rect.getRecLines()) {
            if (isIntersecting(line) && intersectionWith(line) != null) {
                counter++;
            }
        }

        if (counter == 0 && this.end().isPointInsideRectangle(this.end(), rect)) {
            Point p = this.end();
            return p.getClosestPointOnPerimeter(rect);
        } else if (counter == 0) {
            return null;
        }
        /*
         * find all the intersection points and return the closest one to the
         *  start point of
         */
        ArrayList<Point> intersections = (ArrayList<Point>)
                rect.intersectionPoints(this);
        return findClosestPoint(intersections, this);
    }

    /**
     * Finds the closest point in a list of intersection points to the start
     * point of a given line.
     *
     * @param intersectionPoints An ArrayList of Points that represent
     *                           intersection points between a line and another
     *                           object.
     * @param line               The line that intersects with another object.
     * @return The closest point in the intersectionPoints list to the start
     * point of the given line.
     */
    public static Point findClosestPoint(ArrayList<Point> intersectionPoints,
                                         Line line) {
        double minDistance = line.start().distance(intersectionPoints.get(0));
        Point closestPoint = intersectionPoints.get(0);
        /*
         * Iterate through the list of intersection points and find the closest
         *  one to the start point of the line.
         */
        for (Point point : intersectionPoints) {
            if (line.start().distance(point) < minDistance) {
                closestPoint = point;
            }
        }
        return closestPoint;
    }
}
