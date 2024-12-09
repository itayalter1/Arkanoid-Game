// 206132284 Itay Alter
package Objects;

import java.util.Random;

/**
 * This class represents a two-dimensional vector, which has a size and a
 * direction. The class provides methods to construct a velocity object from an
 * angle and a speed, to apply a velocity on a point object, and to set/get the
 * values of the x and y components of the velocity. The class also provides
 * methods to generate random values for the x and y components of the
 * velocity.
 */
public class Velocity {
    private double dx;
    private double dy;
    static final int ANGLE_CORRECTION = 90;
    static final int BINARY_BASE = 2;

    /**
     * constructs a new Objects.Velocity object with the given dx and dy values,
     * which represent the change in the x-axis and y-axis coordinates,
     * respectively. The dx value indicates the change in the x-axis coordinate
     * and the dy value indicates the change in the y-axis coordinate. The
     * resulting velocity vector is (dx, dy).
     *
     * @param dx change in x-axis coordinate
     * @param dy change in y-axis coordinate
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This constructor creates a new Objects.Velocity instance using an existing
     * velocity.
     *
     * @param v the Objects.Velocity instance to copy.
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }


    /**
     * Returns a new velocity object from a given angle and speed. The angle is
     * measured in degrees where 0 degrees points up and the angle increases
     * counterclockwise.
     *
     * @param angle the angle of the desired velocity
     * @param speed the speed of the desired velocity
     * @return a new Objects.Velocity object with the given angle and speed
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle - ANGLE_CORRECTION)) * speed;
        double dy = Math.sin(Math.toRadians(angle - ANGLE_CORRECTION)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * From velocity to speed double.
     *
     * @return the double
     */
    public double fromVelocityToSpeed() {
        double speed = Math.sqrt(Math.pow(this.getDx(), BINARY_BASE)
                + Math.pow(this.getDy(), BINARY_BASE));
        return speed;
    }

    /**
     * Takes a point and applies the current velocity on it, meaning that it
     * moves the point according to the velocity. The new coordinates of the
     * point are calculated by adding the velocity's dx and dy to the x and y
     * coordinates of the given point, respectively. The method returns a new
     * point with the new coordinates.
     *
     * @param p the point to apply the velocity on
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }

    /**
     * Sets the value of the x component of this velocity.
     *
     * @param dx the new value of the x component
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the value of the y component of this velocity.
     *
     * @param dy the new value of the y component
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Returns the change in x-coordinate represented by this velocity.
     *
     * @return the change in x-coordinate represented by this velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the change in x-coordinate represented by this velocity.
     *
     * @return the change in x-coordinate represented by this velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Generates a random dx or dy value between 1 and the given maximum value.
     *
     * @param max the maximum value to generate the random double
     * @return a random dx or dy value between 1 and the given maximum value.
     */
    public static double randomDxOrDy(double max) {
        Random random = new Random();
        return random.nextDouble() * max + 1;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }
}