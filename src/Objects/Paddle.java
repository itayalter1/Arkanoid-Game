// 206132284 Itay Alter
package Objects;

import Interfaces.Collidable;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * This class represents a paddle object in a game. It implements the
 * Sprite and Collidable Interfaces. The class has a Block object named
 * paddle, which is the main component of the paddle. The paddle can move left
 * and right using the moveLeft() and moveRight() methods. It can also detect
 * collisions with other objects using the hit() method, which returns the new
 * velocity of an object that collides with the paddle. The paddle has several
 * regions, and each region has a different angle of deflection when hit. The
 * Paddle class has methods to add itself to a Game, draw itself on a
 * DrawSurface, and update itself as time passes.
 */
public class Paddle implements Sprite, Collidable {
    private Block paddle;
    private biuoop.KeyboardSensor keyboard;
    private Line region1;
    private Line region2;
    private Line region3;
    private Line region4;
    private Line region5;
    private int paddleSpeed;
    private double leftScreen = 20;
    private double rightScreen;
    static final double PADDLE_HEIGHT = 25;
    static final int FIRST_REGION = 0;
    static final int SECOND_REGION = 1;
    static final int MIDDLE_REGION = 2;
    static final int FORTH_REGION = 3;
    static final int LAST_REGION = 4;
    static final double FIRST_REGION_ANGLE = 300;
    static final double SECOND_REGION_ANGLE = 330;
    static final double MIDDLE_REGION_ANGLE = 0;
    static final double FORTH_REGION_ANGLE = 30;
    static final double LAST_REGION_ANGLE = 60;
    static final int RIGHT_BOUND_WIDTH = 780;
    static final int PADDLE_STEP = 7;

    /**
     * Constructs a Paddle object with a predefined size and position, and
     * initializes its regions, keyboard sensor, and the right screen boundary.
     * The color of the paddle is generated randomly.
     *
     * @param game        the GUI object of the game
     * @param width       the width of the paddle
     * @param paddleSpeed the speed of the paddle
     * @param upperLeft the upper left point of the paddle .
     */
    public Paddle(biuoop.GUI game, int width, int paddleSpeed, Point upperLeft) {
        Rectangle rectangle = new Rectangle(upperLeft, width, PADDLE_HEIGHT);
        this.paddle = new Block(rectangle, Ball.randomColor(Color.cyan));
        this.region1 = this.getCollisionRectangle().separateRegions()
                [FIRST_REGION];
        this.region2 = this.getCollisionRectangle().separateRegions()
                [SECOND_REGION];
        this.region3 = this.getCollisionRectangle().separateRegions()
                [MIDDLE_REGION];
        this.region4 = this.getCollisionRectangle().separateRegions()
                [FORTH_REGION];
        this.region5 = this.getCollisionRectangle().separateRegions()
                [LAST_REGION];
        this.keyboard = game.getKeyboardSensor();
        this.rightScreen = RIGHT_BOUND_WIDTH - paddle.getCollisionRectangle().
                getWidth();
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * This method moves the paddle to the left. If the new position of the
     * paddle is out of the screen (less than the leftScreen value), the method
     * sets the paddle's x-coordinate to the leftScreen value. Otherwise, the
     * paddle's x-coordinate is updated to the new value.
     */
    public void moveLeft() {
        Point tempUpperLeft = this.getCollisionRectangle().getUpperLeft();
        tempUpperLeft.setX(tempUpperLeft.getX() - paddleSpeed);
        if (tempUpperLeft.getX() < this.leftScreen) {
            this.getCollisionRectangle().getUpperLeft().setX(leftScreen);
        } else {
            this.getCollisionRectangle().getUpperLeft().setX(tempUpperLeft.getX());
        }
    }

    /**
     * This method moves the paddle to the right. If the new position of the
     * paddle is out of the screen (less than the rightScreen value), the method
     * sets the paddle's x-coordinate to the rightScreen value. Otherwise, the
     * paddle's x-coordinate is updated to the new value.
     */
    public void moveRight() {
        Point tempUpperLeft = this.getCollisionRectangle().getUpperLeft();
        tempUpperLeft.setX(tempUpperLeft.getX() + paddleSpeed);
        if (tempUpperLeft.getX() > this.rightScreen) {
            this.getCollisionRectangle().getUpperLeft().setX(rightScreen);
        } else {
            this.getCollisionRectangle().getUpperLeft().setX(tempUpperLeft.getX());
        }
    }

    /**
     * Draws the paddle on the given DrawSurface object.
     *
     * @param d the DrawSurface to draw the paddle on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    /**
     * Moves the paddle based on what key the user pressed in the keyboard. if
     * he pressed on the "left key" it calls to "moveLeft" method. if he pressed
     * on the "left key" it calls to "moveLeft" method.
     */
    public void timePassed() {
        if (keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        this.getCollisionRectangle().setRecLines(this.getCollisionRectangle());
        this.setRegions(this.getCollisionRectangle());
    }

    /**
     * Return the shape of the block.
     *
     * @return the shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * This method calculates and returns the new velocity of the ball after
     * hitting the paddle, depending on where the ball hits the paddle. It takes
     * as input the point where the collision occurred and the current velocity
     * of the ball before the hit. The method first checks in which region the
     * collision occurred and sets the new velocity according to the
     * corresponding angle for that region. Then it returns the new velocity.
     *
     * @param collisionPoint  the point where the collision occurred.
     * @param currentVelocity the current velocity of the ball before the hit.
     * @param hitter          the ball that hits the paddle.
     * @return the new velocity of the ball after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // hits the first region of the paddle.
        if (region1.start().getX() <= collisionPoint.getX()
                && collisionPoint.getX() <= region1.end().getX()) {
            currentVelocity.setVelocity(currentVelocity.fromAngleAndSpeed(
                    FIRST_REGION_ANGLE, currentVelocity.fromVelocityToSpeed()));
            // hits the second region of the paddle.
        } else if (region2.start().getX() <= collisionPoint.getX()
                && collisionPoint.getX() <= region2.end().getX()) {
            currentVelocity.setVelocity(currentVelocity.fromAngleAndSpeed(
                    SECOND_REGION_ANGLE, currentVelocity.fromVelocityToSpeed()));
            // hits the middle region of the paddle.
        } else if (region3.start().getX() <= collisionPoint.getX()
                && collisionPoint.getX() <= region3.end().getX()) {
            currentVelocity.setVelocity(currentVelocity.fromAngleAndSpeed(
                    MIDDLE_REGION_ANGLE, currentVelocity.fromVelocityToSpeed()));
            // hits the forth region of the paddle.
        } else if (region4.start().getX() <= collisionPoint.getX()
                && collisionPoint.getX() <= region4.end().getX()) {
            currentVelocity.setVelocity(currentVelocity.fromAngleAndSpeed(
                    FORTH_REGION_ANGLE, currentVelocity.fromVelocityToSpeed()));
            // hits the last region of the paddle.
        } else if (region5.start().getX() <= collisionPoint.getX()
                && collisionPoint.getX() <= region5.end().getX()) {
            currentVelocity.setVelocity(currentVelocity.fromAngleAndSpeed(
                    LAST_REGION_ANGLE, currentVelocity.fromVelocityToSpeed()));
        }
        return currentVelocity;
    }

    /**
     * Adds the paddle to the given game by adding it as both a collidables and
     * a sprites lists in game.
     *
     * @param g the game to which the paddle should be added
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Sets the regions of the paddle based on its shape.
     *
     * @param rec the shape of the paddle.
     */
    public void setRegions(Rectangle rec) {
        this.region1 = rec.separateRegions()[FIRST_REGION];
        this.region2 = rec.separateRegions()[SECOND_REGION];
        this.region3 = rec.separateRegions()[MIDDLE_REGION];
        this.region4 = rec.separateRegions()[FORTH_REGION];
        this.region5 = rec.separateRegions()[LAST_REGION];
    }
}
