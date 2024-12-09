// 206132284 Itay Alter
package Objects;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The "KeyPressStoppableAnimation" class implements the "Animation" interface and
 * represents an animation that can be stopped by pressing a specified key on the
 * keyboard. It contains properties such as the animation to be displayed, the
 * key to listen for, and the keyboard sensor. The class provides a method to
 * render a frame of the animation by invoking the "doOneFrame" method of the
 * underlying animation. It also checks if the specified key is pressed and updates
 * the stop flag accordingly. The animation stops when the key is pressed for the
 * first time after the animation has started.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private String key;
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Creates a new instance of the KeyPressStoppableAnimation class.
     *
     * @param sensor The KeyboardSensor used to detect key presses.
     * @param key The key that stops the animation when pressed.
     * @param animation The animation to be wrapped and controlled.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Executes one frame of the animation. It delegates the execution to the
     * wrapped animation object. If the specified key is pressed and was not
     * previously pressed, the animation will be stopped.
     *
     * @param d The DrawSurface on which the animation is drawn.
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        // Check if the specified key is pressed and was not previously pressed
        if (this.keyboard.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
        } else if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Determines whether the animation should stop or continue running.
     *
     * @return True if the animation should stop, False if it should continue running.
     */
    public boolean shouldStop() {
        return stop;
    }
}
