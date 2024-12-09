// 206132284 Itay Alter
package Objects;

import Interfaces.Collidable;

/**
 * This class represents information about a collision that occurred between two
 * objects. It contains a collisionPoint field that represents the point at which
 * the collision occurred, and a collisionObject field that represents the
 * collidable object involved in the collision. It has a constructor to
 * initialize the collisionPoint and collisionObject fields, and getter methods
 * to access the values of these fields. It also has a setter method to modify
 * the collisionPoint field.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Creates a new Objects.CollisionInfo object with the specified collision
     * point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurred
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point as a Point
     */

    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Sets the point at which the collision occurs.
     *
     * @param collisionPoint the new collision point to set
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }
}
