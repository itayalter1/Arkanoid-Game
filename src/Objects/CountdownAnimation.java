// 206132284 Itay Alter
package Objects;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The "CountdownAnimation" class is responsible for displaying a countdown
 * animation on the game screen. It implements the Animation interface. It takes
 * the duration of the animation in seconds, the starting count value, and a
 * collection of sprites representing the game screen as parameters. In each
 * frame of the animation, it draws the game screen sprites on the provided
 * DrawSurface, along with the current count value as a large message at the
 * center of the screen. The count value is decremented after each frame. The
 * animation stops when the count reaches zero and returns true from the "shouldStop"
 * method.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    static final int HALF = 2;
    static final int SLEEP_TIME = 800;
    static final int FONT_SIZE = 80;


    /**
     * Constructs a CountdownAnimation object with the specified duration,
     * starting count, and game screen.
     *
     * @param numOfSeconds The duration of the countdown in seconds.
     * @param countFrom The starting count value.
     * @param gameScreen The SpriteCollection representing the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }
    /**
     * Performs one frame of the countdown animation.
     *
     * @param d The DrawSurface to draw the animation on.
     */
    public void doOneFrame(DrawSurface d) {
        // Draw the game screen
        this.gameScreen.drawAllOn(d);
        // Draw the countdown message
        ScreenMessage message = new ScreenMessage(Integer.toString(this.countFrom));
        message.drawOn(d, FONT_SIZE, d.getWidth() / HALF, d.getHeight() / HALF);
        // Decrement the count
        countFrom--;
    }
    /**
     * Checks if the countdown animation should stop. The method determines whether
     * the countdown animation has completed based on the current count value.
     * It pauses the animation for a specific duration and then checks if the
     * countdown has reached zero.
     *
     * @return true if the countdown is completed and should stop, false otherwise.
     */
    public boolean shouldStop() {
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor(SLEEP_TIME);
        if (this.countFrom == 0) {
            return true;
        }
        return false;
    }
}
