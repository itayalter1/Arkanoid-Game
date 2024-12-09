// 206132284 Itay Alter
package Objects;

import Interfaces.HitListener;

/**
 * The BallRemover class is responsible for removing balls from the game when
 * they go out of the screen boundaries. It implements the HitListener interface
 * and keeps track of the remaining balls in the game.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;


    /**
     * Constructs a BallRemover object with the specified game. The remaining
     * balls counter is initialized with a default value.
     *
     * @param gameLevel the game object
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        this.remainingBalls = new Counter();
    }

    /**
     * Returns the counter for the remaining balls.
     *
     * @return the counter for the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
    /**
     * Removes the ball from the game and updates the remaining balls counter.
     * This method is called when a ball hits a block. It removes the ball from
     * the game and decreases the remaining balls counter.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease();
    }
    /**
     * Sets the Counter object representing the remaining balls in the game.
     *
     * @param remainingBalls the Counter object representing the remaining balls.
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }
}
