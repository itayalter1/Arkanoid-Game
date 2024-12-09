// 206132284 Itay Alter
package Objects;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The "AnimationRunner" class is responsible for running animations in a game.
 * It manages the timing and display of animation frames. It takes a GUI as a
 * parameter, sets the desired frames per second, and uses a sleeper to control
 * the timing. The run method executes the animation loop, where it repeatedly
 * calls the "doOneFrame" method of the animation, updates the GUI display, and
 * ensures a consistent frame rate. The loop continues until the animation
 * indicates that it should stop
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructs an AnimationRunner object with the specified GUI.
     *
     * @param gui the GUI to be used for displaying the animation
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * Runs the provided Animation.
     *
     * @param animation the Animation object to be run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns the GUI object associated with the animation.
     *
     * @return the GUI object.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Returns the Sleeper object associated with the animation.
     *
     * @return the Sleeper object.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }
}
