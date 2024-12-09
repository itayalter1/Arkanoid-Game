// 206132284 Itay Alter
package Objects;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The ScoreIndicator class is responsible for displaying the current score
 * on the game screen. It implements the Sprite interface and is drawn as a
 * block with the score value.
 */
public class ScoreIndicator implements Sprite {
    private Block frame;
    private ScoreTrackingListener score;
    private String levelName;
    static final int LEFT_BOUND_WIDTH = 800;
    static final int BOTTOM_SCREEN = 20;
    static final double X_ORIGIN = 0;
    static final double Y_ORIGIN = 0;
    static final int START_OF_SCORE_X = 390;
    static final int START_OF_TEXT_Y = 15;
    static final int FONT_SIZE = 15;
    static final int START_OF_NAME_X = 600;
    static final String SCORE = "score: ";
    static final String LEVEL_NAME = "level name: ";

    /**
     * Constructs a ScoreIndicator object with the given ScoreTrackingListener.
     *
     * @param score the ScoreTrackingListener object to track the score
     *
     */
    public ScoreIndicator(ScoreTrackingListener score) {
        this.score = score;
        this.frame = new Block(new Point(X_ORIGIN, Y_ORIGIN), LEFT_BOUND_WIDTH,
                BOTTOM_SCREEN, Color.white);
    }

    /**
     * Constructs a ScoreIndicator with the default settings. Initializes the
     * score and frame components.
     */
    public ScoreIndicator() {
        this.score = new ScoreTrackingListener(new Counter());
        this.frame = new Block(new Point(X_ORIGIN, Y_ORIGIN), LEFT_BOUND_WIDTH,
                BOTTOM_SCREEN, Color.white);
    }
    /**
     * Sets the name of the level.
     *
     * @param levelName the name of the level.
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Returns the ScoreTrackingListener associated with the ScoreIndicator.
     *
     * @return the ScoreTrackingListener object associated with the
     * ScoreIndicator
     */
    public ScoreTrackingListener getScore() {
        return this.score;
    }

    /**
     * Returns the Block object representing the frame of the ScoreIndicator.
     *
     * @return the Block object representing the frame of the ScoreIndicator
     */
    public Block getFrame() {
        return frame;
    }

    /**
     * Draws the ScoreIndicator on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the ScoreIndicator
     */
    @Override
    public void drawOn(DrawSurface d) {
        int score = this.score.getCurrentScore().getValue();
        String name = this.levelName;
        this.frame.drawOn(d);
        d.drawText(START_OF_SCORE_X, START_OF_TEXT_Y, SCORE + score, FONT_SIZE);
        d.drawText(START_OF_NAME_X, START_OF_TEXT_Y, LEVEL_NAME + name, FONT_SIZE);
    }

    /**
     * This method is not used by the ScoreIndicator and has no effect.
     */
    @Override
    public void timePassed() {
    }
}
