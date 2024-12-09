// 206132284 Itay Alter
package Interfaces;

import biuoop.DrawSurface;

/**
 * The "Animation" interface provides the blueprint for an animation in a game.
 * It defines the required behavior for an animation without specifying the exact
 * details of its methods. Classes that implement this interface are expected to
 * have methods that handle rendering individual frames "doOneFrame" and determine
 * when the animation should stop "shouldStop".
 */
public interface Animation {
    /**
     * Renders a single frame of the animation on the provided drawing surface.
     *
     * @param d The drawing surface on which to render the frame.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}
