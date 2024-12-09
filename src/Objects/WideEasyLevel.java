// 206132284 Itay Alter
package Objects;

import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The WideEasyLevel class implements the LevelInformation interface and
 * represents a specific level in the game. It defines the properties of the level,
 * such as the number and velocity of balls, paddle speed and width, level name,
 * background, blocks, and the number of blocks to remove. The class initializes
 * the blocks and balls specific to this level. It also includes constants for
 * block dimensions, ball speed and angle, screen dimensions, and paddle position.
 */
public class WideEasyLevel implements LevelInformation {
    private ArrayList<Block> blocks;
    private ArrayList<Ball> balls;
    static final double MOST_UPPER_RIGHT_BLOCK_X = 728;
    static final double MOST_UPPER_RIGHT_BLOCK_Y = 270;
    static final int BLOCK_LINES_AMOUNT = 1;
    static final int BLOCK_HEIGHT = 25;
    static final int BLOCK_WIDTH = 52;
    static final int BLOCKS_AMOUNT = 15;
    static final double GAME_BALL_START_SPEED = 4;
    static final double GAME_BALL_START_ANGLE1 = 305;
    static final double GAME_BALL_START_ANGLE2 = 15;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final double X_ORIGIN = 0;
    static final double Y_ORIGIN = 0;
    static final double PADDLE_UPPER_LEFT_X = 135;
    static final double PADDLE_UPPER_LEFT_Y = 555;
    static final int PADDLE_WIDTH = 555;
    static final int PADDLE_SPEED = 3;
    static final int NUM_OF_BALLS = 10;
    static final String LEVEL_NAME = "Wide Easy";


    /**
     * Constructs a new instance of the WideEasyLevel class.
     * Initializes the list of blocks and the list of balls for the level.
     * The blocks are randomly generated using the Block.randomBlocks method,
     * and the balls are randomly generated using the Ball.randomGameBall method.
     * The level has a wide layout with multiple lines of blocks and a specified
     * number of balls.
     */
    public WideEasyLevel() {
        this.blocks = new ArrayList<>();
        this.blocks.addAll(Block.randomBlocks(BLOCK_LINES_AMOUNT, BLOCK_WIDTH,
                BLOCK_HEIGHT, BLOCKS_AMOUNT, MOST_UPPER_RIGHT_BLOCK_X, MOST_UPPER_RIGHT_BLOCK_Y));
        this.balls = new ArrayList<>();
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
        double gameBallAngle1 = GAME_BALL_START_ANGLE1;
        double gameBallAngle2 = GAME_BALL_START_ANGLE2;
        ArrayList<Velocity> velocities = new ArrayList<>();
        // Set the velocities for the first half of the balls with increasing angles
        for (int i = 0; i < this.balls.size() / 2; i++) {
            this.balls.get(i).setVelocity(this.balls.get(i).getVelocity().fromAngleAndSpeed(
                   gameBallAngle1 + (10 * i), GAME_BALL_START_SPEED));
            velocities.add(this.balls.get(i).getVelocity());
        }
        int j = 0;
        // Set the velocities for the second half of the balls with increasing angles
        for (int i = this.balls.size() / 2; i < this.balls.size(); i++) {
            this.balls.get(i).setVelocity(this.balls.get(i).getVelocity().fromAngleAndSpeed(
                    gameBallAngle2 + (10 * j), GAME_BALL_START_SPEED));
            velocities.add(this.balls.get(i).getVelocity());
            j++;
        }
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
     * Draws the background of the game using the given DrawSurface.
     * The background is filled with white color, representing a clear sky.
     * The sun with rays is drawn at the specified position and size.
     *
     * @param d The DrawSurface to draw on.
     */
    public void drawBackground(DrawSurface d) {
        // Fill the background with white color
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw the sun with rays
        this.drawSun(d, d.getWidth() / 4, d.getHeight() / 4, 50, d.getWidth(), 270);
    }

    /**
     * Draws a sun with rays on the given DrawSurface at the specified position and size.
     * The sun is represented by a circle with outer rays pointing downwards.
     * The colors of the sun and rays are calculated based on the base color values.
     *
     * @param d The DrawSurface to draw on.
     * @param centerX The x-coordinate of the center of the sun.
     * @param centerY The y-coordinate of the center of the sun.
     * @param radius The radius of the sun.
     * @param screenWidth The width of the screen.
     * @param rayEndY The y-coordinate where the rays of the sun end.
     */
    private void drawSun(DrawSurface d, int centerX, int centerY, int radius, int screenWidth, int rayEndY) {
        // Base color values
        int baseRed = 255;
        int baseGreen = 255;
        int baseBlue = 100;
        // Calculate color shades
        Color shade1 = new Color(baseRed, baseGreen - 37, baseBlue + 85);
        Color shade2 = new Color(baseRed - 40, baseGreen - 40, baseBlue - 100);
        // Draw the rays of the sun pointing downwards
        d.setColor(shade1);
        for (int x = 0; x <= screenWidth; x += 10) {
            d.drawLine(centerX, centerY, x, rayEndY);
        }
        // Draw the brighter circle around the sun
        d.setColor(shade1);
        int brighterCircleRadius = radius + 10;
        int brighterCircleCenterX = centerX;
        int brighterCircleCenterY = centerY;
        d.fillCircle(brighterCircleCenterX, brighterCircleCenterY, brighterCircleRadius);
        // Draw the outer circle of the sun
        d.setColor(shade2);
        d.fillCircle(centerX, centerY, radius);
        // Draw the inner circle of the sun
        d.setColor(Color.yellow);
        d.fillCircle(centerX, centerY, radius - 10);
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
        return new Block(rec, Color.white);
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
    public ArrayList<Ball> balls() {
        return this.balls;
    }
}
