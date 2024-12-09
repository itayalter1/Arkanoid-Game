// 206132284 Itay Alter
package Objects;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The "PauseScreen" class implements the "Animation" interface and represents a
 * pause screen in a game. It displays the "Paused" message and a prompt to press
 * the space key to continue. It listens to the keyboard sensor for the space key
 * press, and if detected, it sets the stop flag to true, indicating that the
 * animation should stop.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    static final int PAUSE_FONT_SIZE = 70;
    static final int CONTINUE_FONT_SIZE = 40;

    /**
     * Constructs a PauseScreen animation with the specified keyboard sensor.
     *
     * @param k the keyboard sensor for user input
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Draws the pause screen on the given draw surface.
     *
     * @param d the draw surface on which to draw the pause screen
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 3, d.getHeight() / 3, "Paused", PAUSE_FONT_SIZE);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                "Press space to continue", CONTINUE_FONT_SIZE);
    }

    /**
     * Checks if the PauseScreen animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
