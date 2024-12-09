// 206132284 Itay Alter
package Interfaces;

import Objects.Ball;
import Objects.Block;
import Objects.Point;
import Objects.Velocity;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The "LevelInformation" interface provides information about a game level
 * without specifying the details of its methods. It encapsulates properties such
 * as the number of balls, initial ball velocities, paddle speed and width, level
 * name, background sprite, blocks configuration, number of blocks to remove, balls
 * collection, and the upper-left position of the paddle.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    int numberOfBalls();

    /**
     * Returns a list of initial velocities for the balls in the level.
     *
     * @return a list of initial velocities for the balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return the name of the level
     */
    String levelName();

    /**
     * Returns the background of the level.
     *
     * @return the background of the level.
     */
    Sprite getBackground();

    /**
     * Returns the blocks in the level.
     *
     * @return the blocks in the level as an ArrayList
     */
    ArrayList<Block> blocks();

    /**
     * Returns the number of blocks that need to be removed in order to complete
     * the level.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();

    /**
     * Returns the balls in the level.
     *
     * @return the balls in the level as an ArrayList
     */
    ArrayList<Ball> balls();

    /**
     * Return the Paddle upper left point.
     *
     * @return the Paddle upper left point
     */
    Point paddleUpperLeft();

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the background on.
     */
     void drawBackground(DrawSurface d);
}
