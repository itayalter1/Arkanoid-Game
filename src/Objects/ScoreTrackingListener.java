// 206132284 Itay Alter
package Objects;
import Interfaces.HitListener;

/**
 * The ScoreTrackingListener class is responsible for tracking the score in the
 * game. It implements the HitListener interface and keeps track of the current
 * score by increasing it whenever a block is hit by a ball.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    static final int SCORE_INCREASE = 5;

    /**
     * Constructs a ScoreTrackingListener object with the given score counter.
     *
     * @param scoreCounter the counter to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Initializes the current score counter.
     */
    public void initCurrentScore() {
        this.currentScore = new Counter();
    }

    /**
     * Returns the current score counter.
     *
     * @return the current score counter
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
    /**
     * Updates the score when a block is hit. This method increases the current
     * score by 5 when a block is hit by a ball.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SCORE_INCREASE);
    }
}
