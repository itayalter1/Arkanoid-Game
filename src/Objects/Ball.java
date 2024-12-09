// 206132284 Itay Alter
package Objects;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * This class represents a ball object. It contains variables and methods for
 * storing and manipulating the ball's position, velocity, size, and color.
 * This class can be used in programs that involve simulations, games, or other
 * applications that require ball objects.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    static final double EPSILON = 0.000015;
    static final int GAME_BALL_RADIUS = 5;
    static final int COLOR_INTENSITY = 256;
    static final int PADDLE_UPPER_LEFT_X = 340;
    static final int PADDLE_UPPER_LEFT_Y = 555;
    static final int PADDLE_WIDTH = 120;

    /**
     * Constructs a ball object with the given center point, radius, color, and
     * initial velocity and his movement boundaries.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the gameEnvironment the ball will be in
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment
            gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * Constructs a ball object with the given center point, radius, color, and
     * initial velocity and his movement boundaries.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }
    /**
     * Returns the X coordinate of this center point of the ball.
     *
     * @return the X coordinate of this center point of the ball.
     */
// accessors
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Returns the Y coordinate of this center point of the ball.
     *
     * @return the Y coordinate of this center point of the ball.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Returns the radius off the ball.
     *
     * @return the radius off the ball.
     */
    public int getSize() {
        return radius;
    }

    /**
     * Returns the color off the ball.
     *
     * @return the color off the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface object. The ball is filled with
     * white color, has a black outline and a small red dot at its center.
     *
     * @param d the DrawSurface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillCircle(this.getX(), this.getY(), this.radius);
        d.setColor(Color.black);
        d.drawCircle(this.getX(), this.getY(), this.radius);
        d.setColor(Color.red);
        d.fillCircle(this.getX(), this.getY(), 1);

    }

    @Override
    /**
     * This method updates the ball's position by calling the moveOneStep method.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets the ball's velocity to the given velocity.
     *
     * @param v the new velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball according to the given change in position
     * in x and y directions.
     *
     * @param dx the change in position in x direction
     * @param dy the change in position in y direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the velocity off the ball.
     *
     * @return the velocity off the ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }


    /**
     * Move the ball one step according to its velocity and the collidables in
     * the game environment. If there is no collision, the ball's center is
     * updated according to its velocity. If there is a collision, the ball's
     * velocity is updated according to the colliding object and point. Also,
     * the ball's center is updated according to the new velocity.
     */
    public void moveOneStep() {
        // Create a temporary ball object to hold the updated center point
        Ball tempBall = new Ball(this.center, this.radius, this.color,
                this.gameEnvironment);
        CollisionInfo collisionInfo;
        // Update the center point of the temporary ball.
        tempBall.center = this.getVelocity().applyToPoint(tempBall.center);
        double newX = tempBall.center.getX();
        double newY = tempBall.center.getY();
        Line trajectory = new Line(this.center, new Point(newX, newY));
        // Check if there is a collision along the trajectory.
        collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        // If no collision, update the center point according to the velocity.
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            /*
             * If there is a collision, update the velocity according to the
             *  colliding object and point.
             */
            Velocity newVelocity = collisionInfo.collisionObject().
                    hit(this, collisionInfo.collisionPoint(), this.velocity);
            this.center = collisionInfo.collisionPoint();
            if (newVelocity.getDx() < 0) {
                this.center.setX(this.center.getX() - EPSILON);
            }
            if (newVelocity.getDx() > 0) {
                this.center.setX(this.center.getX() + EPSILON);
            }
            if (newVelocity.getDy() < 0) {
                this.center.setY(this.center.getY() - EPSILON);
            }
            if (newVelocity.getDy() > 0) {
                this.center.setY(this.center.getY() + EPSILON);
            }
            this.velocity = newVelocity;
        }
    }

    /**
     * Sets the game environment of the ball.
     *
     * @param gameEnvironment the game environment to set.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Generates a random color using the RGB color model.
     *
     * @param color the color that the new color that the method generates can't
     *              be the same.
     * @return random color
     */
    public static java.awt.Color randomColor(Color color) {
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(COLOR_INTENSITY);
        int green = randomGenerator.nextInt(COLOR_INTENSITY);
        int blue = randomGenerator.nextInt(COLOR_INTENSITY);
        Color newColor = new Color(red, green, blue);
        while (newColor == color) {
            randomColor(color);
        }
        return newColor;
    }

    /**
     * Creates a random game ball with a given game environment.
     *
     * @param gameEnvironment the game environment in which the ball is created
     * @return a Ball object with random center point, radius, color, and
     * velocity
     */
    public static Ball randomGameBall(GameEnvironment gameEnvironment) {
        int radius = GAME_BALL_RADIUS;
        Point center = Point.randomPoint(PADDLE_UPPER_LEFT_X,
                PADDLE_UPPER_LEFT_Y, PADDLE_UPPER_LEFT_X + PADDLE_WIDTH,
                PADDLE_UPPER_LEFT_Y + 1);
        Ball ball = new Ball(center, radius, randomColor(Color.cyan),
                gameEnvironment);
        return ball;
    }
    /**
     * Creates a random game ball with a given game environment.
     *
     * @return a Ball object with random center point, radius, color.
     */
    public static Ball randomGameBall() {
        int radius = GAME_BALL_RADIUS;
        Point center = new Point(PADDLE_UPPER_LEFT_X + PADDLE_WIDTH / 2,
                    PADDLE_UPPER_LEFT_Y - EPSILON);
        Ball ball = new Ball(center, radius, randomColor(Color.cyan));
        return ball;
    }
    /**
     * Removes the ball from the specified Game.
     *
     * @param gameLevel the Game object from which the Ball will be removed
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
