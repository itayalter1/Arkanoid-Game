// 206132284 Itay Alter
package Objects;

import Interfaces.Collidable;

import java.util.ArrayList;

/**
 * This class represents the environment in which the game objects (Collidables)
 * exist. It maintains a list of Collidables and provides methods to add
 * Collidables to the environment and to retrieve the list of Collidables. The
 * class also provides a method "getClosestCollision". and
 * "closestIntersectionToStartOfLine" method. The class also provides a  method
 * "findClosestPoint".
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables;


    /**
     * Constructor for the GameEnvironment class, creates a new ArrayList of
     * Collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a new collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Returns the list of collidables in the game environment.
     *
     * @return the list of collidables in the game environment.
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * This method receives line representing the trajectory of a ball and
     * returns the closest collision info that it intersects with among all the
     * collidables in the game environment. It does so by iterating over all
     * collidables and calculating the closest intersection point between the
     * trajectory and each collidable's CollisionRectangle.
     * The method then creates a collision info for each intersection point, and
     * finally returns the collision info corresponding to the closest
     * intersection point.
     *
     * @param trajectory a line representing the trajectory to check for
     *                  collisions with collidable objects
     * @return the Objects.CollisionInfo object representing the closest
     * intersection point and the collidable object it collided with, or null if
     * there are no collisions
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<CollisionInfo> intersectionPoints = new ArrayList<>();
        Point currentIntersection;
        /*
         * Iterate through all collidable objects and find the closest
         *  intersection point to the start of the given trajectory
         */
        for (Collidable collidable : this.collidables) {
            currentIntersection = trajectory.closestIntersectionToStartOfLine(
                    collidable.getCollisionRectangle());
            if (currentIntersection == null) {
                continue;
            }
            intersectionPoints.add(new CollisionInfo(currentIntersection,
                    collidable));
        }
        // If no intersection points were found, return null
        if (intersectionPoints.size() == 0) {
            return null;
        }
        /*
         * Find the closest intersection point to the start of the trajectory
         *  and return its Objects.CollisionInfo object
         */
        return findClosestPoint(intersectionPoints, trajectory);
    }

    /**
     * Returns the closest collision info point to the start of the given line
     * out of the given list of collision info points.
     *
     * @param collisionInfo list of collision info points to search through
     * @param line the line to find the closest point to
     * @return the closest collision info point to the start of the given line
     */
    public static CollisionInfo findClosestPoint(
            ArrayList<CollisionInfo> collisionInfo, Line line) {
        double minDistance = line.start().distance(collisionInfo.get(0).
                collisionPoint());
        CollisionInfo closestPoint = collisionInfo.get(0);
        for (CollisionInfo info : collisionInfo) {
            if (line.start().distance(info.collisionPoint()) < minDistance) {
                closestPoint = info;
            }
        }
        return closestPoint;
    }
}
