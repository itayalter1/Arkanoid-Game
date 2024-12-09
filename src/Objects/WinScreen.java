// 206132284 Itay Alter
package Objects;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The "WinScreen" class is responsible for displaying a screen animation when
 * the player wins the game. It implements the "Animation" interface and contains
 * properties such as the keyboard sensor and score indicator. The class provides
 * a method to render a frame of the animation by drawing a victory message and
 * the player's final score on the DrawSurface. It also has a flag to determine
 * when the animation should stop.
 */
public class WinScreen implements Animation {
    private KeyboardSensor keyboard;
    private ScoreIndicator score;
    private boolean stop;
    static final int END_OF_GAME_MESSAGE_PART1_FONT = 35;
    static final int END_OF_GAME_MESSAGE_PART2_FONT = 45;
    static final int START_OF_TEXT_X = 150;
    static final int START_OF_TEXT_Y = 350;

    /**
     * Constructs a new WinScreen with the specified keyboard sensor and score
     * indicator.
     *
     * @param k the keyboard sensor to be used
     * @param score the score indicator to be displayed
     */
    public WinScreen(KeyboardSensor k, ScoreIndicator score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }

    /**
     * Performs a single frame of the WinScreen animation, drawing the
     * congratulatory message and the final score on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the frame
     */
    public void doOneFrame(DrawSurface d) {
        ScreenMessage message = new ScreenMessage("  Well done! You win"
                + " the game!");
        message.drawOn(d, END_OF_GAME_MESSAGE_PART1_FONT);
        d.drawText(START_OF_TEXT_X, START_OF_TEXT_Y, " Your final score is "
                        + this.score.getScore().getCurrentScore().getValue() + "!",
                END_OF_GAME_MESSAGE_PART2_FONT);
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
