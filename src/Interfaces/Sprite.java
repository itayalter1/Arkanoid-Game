// 206132284 Itay Alter
package Interfaces;

import biuoop.DrawSurface;


/**
 * The Sprite interface represents objects that can be drawn on a DrawSurface
 * and updated over time (position / shape / appearance / etc).
 */
public interface Sprite {


    /**
     * Draw the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);


    /**
     * Update the state of the sprite for the next frame.This method is called
     * once per frame by the game loop.
     */
    void timePassed();
}
