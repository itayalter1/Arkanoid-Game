// 206132284 Itay Alter
package Objects;

import Interfaces.HitListener;

/**
 * The BlockRemover class is responsible for removing blocks from the game when
 * they are hit by a ball. It implements the HitListener interface and keeps
 * track of the remaining blocks in the game.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover object with the specified game. The remaining
     * blocks counter is initialized with a default value.
     *
     * @param gameLevel the game object
     */
    public BlockRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = new Counter();
    }

    /**
     * Returns the counter for the remaining blocks.
     *
     * @return the counter for the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
    /**
     * Removes the block from the game and updates the remaining blocks counter.
     * This method is called when a block is hit by a ball. It removes the block
     * from the game and decreases the remaining blocks counter.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease();
    }
    /**
     * Sets the Counter object representing the remaining blocks in the game.
     *
     * @param remainingBlocks the Counter object representing the remaining blocks.
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }
}
