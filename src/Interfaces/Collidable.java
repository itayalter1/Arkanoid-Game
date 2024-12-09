// 206132284 Itay Alter
package Interfaces;

import Objects.Ball;
import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;

/**
 * The Collidable interface represents objects in the game that can be collided
 * with. It includes two methods that must be implemented by any class that
 * implements the interface.
 */
public interface Collidable {

    /**
     * Returns the rectangle that represents the shape and position of the
     * collidable object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the velocity of the object that collided with this
     *                       object
     * @param hitter the ball that hits the collidable.
     * @return the new velocity of the object after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
