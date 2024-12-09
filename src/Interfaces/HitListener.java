// 206132284 Itay Alter
package Interfaces;
import Objects.Ball;
import Objects.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 * Classes that implement this interface can perform specific actions when a hit
 * event occurs.
 */
public interface HitListener {
    /**
     * This method is called whenever the block is hit by a ball.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that performed the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}
