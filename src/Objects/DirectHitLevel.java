// 206132284 Itay Alter
package Objects;

import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "DirectHitLevel" class implements the LevelInformation interface and
 * represents a specific level called "Direct Hit". It contains information about
 * the configuration of the level, including the number of balls, initial ball
 * velocities, paddle speed and width, level name, background sprite, blocks
 * configuration, number of blocks to remove, and the collection of balls.
 */
public class DirectHitLevel implements LevelInformation {
    private ArrayList<Block> blocks;
    private ArrayList<Ball> balls;
    static final double GAME_BALL_START_SPEED = 4;
    static final double GAME_BALL_START_ANGLE = 0;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final double X_ORIGIN = 0;
    static final double Y_ORIGIN = 0;
    static final double PADDLE_UPPER_LEFT_X = 340;
    static final double PADDLE_UPPER_LEFT_Y = 555;
    static final double BLOCK_UPPER_LEFT_X = 380;
    static final double BLOCK_UPPER_LEFT_Y = 150;
    static final int BLOCK_WIDTH = 25;
    static final int BLOCK_HEIGHT = 25;
    static final int PADDLE_WIDTH = 120;
    static final int PADDLE_SPEED = 7;
    static final String LEVEL_NAME = "Direct Hit";

    /**
     * Constructs a DirectHitLevel. Initializes the blocks and balls of the level.
     * The level consists of a single block positioned at the center of the screen,
     * representing the target of the "Direct Hit" level. It also creates a single
     * random game ball.
     */
    public DirectHitLevel() {
        this.blocks = new ArrayList<>();
        this.blocks.add(new Block(new Point(BLOCK_UPPER_LEFT_X, BLOCK_UPPER_LEFT_Y),
                BLOCK_WIDTH, BLOCK_HEIGHT,
                Color.red));
        this.balls = new ArrayList<>();
        this.balls.add(Ball.randomGameBall());
    }

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    public int numberOfBalls() {
        return this.balls.size();
    }

    /**
     * Returns a list of initial velocities for the balls. Set the velocity of
     * the ball using the specified angle and speed. Add the velocity of the ball
     * to the list
     *
     * @return the list of initial velocities
     */
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        this.balls.get(0).setVelocity(this.balls.get(0).getVelocity().fromAngleAndSpeed(
                GAME_BALL_START_ANGLE, GAME_BALL_START_SPEED));
        velocities.add(this.balls.get(0).getVelocity());
        return velocities;
    }

    /**
     * Returns the speed of the paddle.
     *
     * @return the speed of the paddle
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Returns the width of the paddle.
     *
     * @return the width of the paddle
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Return the Paddle upper left point.
     *
     * @return the Paddle upper left point
     */
    public Point paddleUpperLeft() {
        return new Point(PADDLE_UPPER_LEFT_X, PADDLE_UPPER_LEFT_Y);
    }

    /**
     * Draws the background for the game on the given DrawSurface.
     * The background consists of a black color filled rectangle as the background,
     * and a target shape around a specific block defined by its upper-left coordinates (x, y)
     * and its width and height.
     *
     * @param d The DrawSurface to draw the background on.
     */
    public void drawBackground(DrawSurface d) {
        // Set the background color to black
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        // Draw the target around the block
        int blockX = 380;
        int blockY = 150;
        int blockSize = 25;
        int targetRadius = 120;
        // Draw outer circles
        d.setColor(Color.blue);
        d.drawCircle(blockX + blockSize / 2, blockY + blockSize / 2, targetRadius);
        d.drawCircle(blockX + blockSize / 2, blockY + blockSize / 2, targetRadius - 40);
        d.drawCircle(blockX + blockSize / 2, blockY + blockSize / 2, targetRadius - 80);
        // Set color to white for target cross lines
        d.setColor(Color.blue);
        // Calculate the coordinates for the target cross lines
        int targetCrossX = blockX + blockSize / 2;
        int targetCrossY = blockY + blockSize / 2;
        int targetCrossLength = targetRadius;
        // Draw vertical line
        d.drawLine(targetCrossX, targetCrossY - targetCrossLength - 20,
                targetCrossX, targetCrossY + targetCrossLength + 20);
        // Draw horizontal line
        d.drawLine(targetCrossX - targetCrossLength - 20, targetCrossY,
                targetCrossX + targetCrossLength + 20, targetCrossY);
    }

    /**
     * Returns the name of the level.
     *
     * @return the name of the level
     */
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * Returns the background of the level.
     *
     * @return the background of the level.
     */
    public Sprite getBackground() {
        Rectangle rec = new Rectangle(new Point(X_ORIGIN, Y_ORIGIN), SCREEN_WIDTH,
                SCREEN_HEIGHT);
        return new Block(rec, Color.BLACK);
    }

    /**
     * Returns the blocks in the level.
     *
     * @return the blocks in the level as an ArrayList
     */
    public ArrayList<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }

    /**
     * Returns the balls in the level.
     *
     * @return the balls in the level as an ArrayList
     */
    @Override
    public ArrayList<Ball> balls() {
        return this.balls;
    }
}
