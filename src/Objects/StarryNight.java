// 206132284 Itay Alter
package Objects;

import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class implements the "LevelInformation" interface and
 * represents a specific level in the game. It defines the properties of the
 * level, such as the number and velocity of balls, paddle speed and width, level
 * name, background, blocks, and the number of blocks to remove. The class
 * initializes the blocks and balls specific to this level. It also includes
 * constants for block dimensions, ball speed and angle, screen dimensions, and
 * paddle position.
 */
public class StarryNight implements LevelInformation {
    private ArrayList<Block> blocks;
    private ArrayList<Ball> balls;
    static final int BLOCK_LINES_AMOUNT = 6;
    static final int BLOCK_HEIGHT = 25;
    static final int BLOCK_WIDTH = 38;
    static final int BLOCKS_AMOUNT = 15;
    static final double MOST_UPPER_RIGHT_BLOCK_X = 742;
    static final double MOST_UPPER_RIGHT_BLOCK_Y = 100;
    static final double GAME_BALL_START_SPEED = 4;
    static final double GAME_BALL1_START_ANGLE = 45;
    static final double GAME_BALL2_START_ANGLE = 315;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final double X_ORIGIN = 0;
    static final double Y_ORIGIN = 0;
    static final double PADDLE_UPPER_LEFT_X = 340;
    static final double PADDLE_UPPER_LEFT_Y = 555;
    static final int PADDLE_WIDTH = 120;
    static final int PADDLE_SPEED = 7;
    static final int NUM_OF_BALLS = 2;
    static final String LEVEL_NAME = "Starry Night";

    /**
     * Creates a new instance of the StarryNight class. This level contains
     * random blocks and two balls. The method randomly generates blocks and adds
     * them to the level's block list. The blocks are positioned in a specified
     * pattern defined by the constants. The method also creates two random balls
     * and adds them to the level's ball list. The number of blocks, their size,
     * and their position are determined by the constants defined for the
     * Green3Level class.
     */
    public StarryNight() {
        this.blocks = new ArrayList<>();
        // Add random blocks to the level
        this.blocks.addAll(Block.randomBlocks(BLOCK_LINES_AMOUNT, BLOCK_WIDTH,
                BLOCK_HEIGHT, BLOCKS_AMOUNT, MOST_UPPER_RIGHT_BLOCK_X, MOST_UPPER_RIGHT_BLOCK_Y));
        this.balls = new ArrayList<>();
        // Add two random balls to the level
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            this.balls.add(Ball.randomGameBall());
        }
    }

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return this.balls.size();
    }

    /**
     * Returns a list of initial velocities for the balls.
     * The balls are divided into two groups, each with different initial angles.
     *
     * @return the list of initial velocities
     */
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        this.balls.get(0).setVelocity(this.balls.get(0).getVelocity().fromAngleAndSpeed(
                GAME_BALL1_START_ANGLE, GAME_BALL_START_SPEED));
        velocities.add(this.balls.get(0).getVelocity());
        this.balls.get(1).setVelocity(this.balls.get(1).getVelocity().fromAngleAndSpeed(
                GAME_BALL2_START_ANGLE, GAME_BALL_START_SPEED));
        velocities.add(this.balls.get(1).getVelocity());
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
        return new Block(rec, Color.black);
    }

    /**
     * Returns the blocks in the level.
     *
     * @return the blocks in the level as an ArrayList
     */
    public ArrayList<Block> blocks() {
        ArrayList<Block> blocks = Block.randomBlocks(BLOCK_LINES_AMOUNT,
                BLOCK_WIDTH, BLOCK_HEIGHT, BLOCKS_AMOUNT, MOST_UPPER_RIGHT_BLOCK_X,
                MOST_UPPER_RIGHT_BLOCK_Y);
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
    public ArrayList<Ball> balls() {
        return this.balls;
    }

    /**
     * Draws the background for the game on the given DrawSurface.
     * The background consists of a black color filled rectangle as the background,
     * with randomly placed white stars scattered across the screen.
     * Additionally, it includes a moon shape represented by a white circle with
     * a smaller black circle on top.
     *
     * @param d The DrawSurface to draw the background on.
     */
    public void drawBackground(DrawSurface d) {
        // Set the background color
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw stars
        Random random = new Random();
        d.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(d.getWidth());
            int y = random.nextInt(d.getHeight());
            int size = random.nextInt(3) + 1;
            d.fillRectangle(x, y, size, size);
        }
        int x = 100, y = 100, radius = 50;
        // Draw the moon's body
        d.setColor(Color.WHITE);
        d.fillCircle(x, y, radius);
        d.setColor(Color.BLACK);
        d.fillCircle(x + radius / 2, y - radius / 4, radius);
    }

}
