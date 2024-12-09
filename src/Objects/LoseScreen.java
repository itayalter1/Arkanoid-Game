// 206132284 Itay Alter
package Objects;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The "LoseScreen" class is responsible for displaying a screen animation when
 * the player loses the game. It implements the "Animation" interface and contains
 * properties such as the keyboard sensor and score indicator. The class provides
 * a method to render a frame of the animation by drawing a "GAME OVER!!!" message
 * and the player's final score on the DrawSurface. It also has a flag to determine
 * when the animation should stop.
 */
public class LoseScreen implements Animation {
    private KeyboardSensor keyboard;
    private ScoreIndicator score;
    private boolean stop;
    static final int START_OF_TEXT_X = 220;
    static final int START_OF_TEXT_Y = 350;
    static final int GAME_OVER_MESSAGE_PART1_FONT = 80;
    static final int GAME_OVER_MESSAGE_PART2_FONT = 40;

    /**
     * Constructs a new LoseScreen object with the specified KeyboardSensor and
     * ScoreIndicator.
     *
     * @param k The KeyboardSensor used for user input.
     * @param score The ScoreIndicator used to display the score.
     */
    public LoseScreen(KeyboardSensor k, ScoreIndicator score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }

    /**
     * Performs one frame of the LoseScreen animation on the specified DrawSurface.
     *
     * @param d The DrawSurface on which to draw the animation frame.
     */
    public void doOneFrame(DrawSurface d) {
        // Display "GAME OVER!!!!" message on the screen
        ScreenMessage message = new ScreenMessage("GAME OVER!!!!");
        message.drawOn(d, GAME_OVER_MESSAGE_PART1_FONT);
        // Display the player's score on the scree
        d.drawText(START_OF_TEXT_X, START_OF_TEXT_Y, " Your score is "
                        + this.score.getScore().getCurrentScore().getValue() + "!",
                GAME_OVER_MESSAGE_PART2_FONT);
    }

    /**
     * Checks if the LoseScreen animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
