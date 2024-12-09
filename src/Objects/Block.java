// 206132284 Itay Alter
package Objects;

import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;


/**
 * This class represents a block that can collide with other objects and can be
 * drawn on a surface. It implements the Collidable and Sprite interfaces, and
 * has a rectangular shape and a color. It provides methods for getting the
 * collision rectangle, updating the velocity upon collision, drawing the block
 * on a surface, and generating a random set of blocks.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Color color;
    private ArrayList<HitListener> hitListeners;
    private Color[] colorsPalette = {Color.BLUE, Color.gray, Color.MAGENTA,
            Color.ORANGE, Color.PINK, Color.yellow, Color.red, Color.black};
    static final double EPSILON = 0.00001;
    static final double MOST_UPPER_RIGHT_BLOCK_X = 742;
    static final double X_ORIGIN = 0;
    static final double Y_ORIGIN = 15;
    static final int LEFT_BOUND_WIDTH = 20;
    static final int RIGHT_BOUND_WIDTH = 780;
    static final int BOTTOM_SCREEN = 800;
    static final int HALF = 2;

    /**
     * Constructor for Block object with a given rectangle shape and color.
     *
     * @param shape the shape of the Objects.Block
     * @param color the color of the Objects.Block
     */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor for a Block object with the given upper left point, width,
     * height, and color.
     *
     * @param upperLeft the upper left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Return the shape of the block.
     *
     * @return the shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * This method receives a point representing the collision point and the
     * current velocity of an object that hit this block.  It calculates and
     * returns the new velocity of the object after hitting the block, based on
     * the side of the block that was hit. It checks which side of the block
     * was hit by comparing the y and x values of the collision point to the
     * block's coordinates. If the top or bottom side of the block was hit, the
     * method negates the dy value of the velocity. If the left or right side
     * was hit, it negates the dx value. Finally,the method notify the listeners
     * about the hit event, returns the new velocity with the updated dx and dy
     * values.
     *
     * @param collisionPoint  the point of collision with the block
     * @param currentVelocity the current velocity of the object
     * @param hitter          the ball that hits the block.
     * @return the new velocity of the object after hitting the block
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();
        // Check which side of the paddle was hit
        if (Math.abs(collisionY - this.shape.getUpperLeft().getY()) < EPSILON) {
            // Top side of the block was hit
            dy = -dy;
        } else if (Math.abs(collisionY - this.shape.getUpperLeft().getY()
                - this.shape.getHeight()) < EPSILON) {
            // Bottom side of the block was hit
            dy = -dy;
        } else if (Math.abs(collisionX - this.shape.getUpperLeft().getX())
                < EPSILON) {
            // Left side of the block was hit
            dx = -dx;
        } else if (Math.abs(collisionX - (this.shape.getUpperLeft().getX()
                + this.shape.getWidth())) < EPSILON) {
            // Right side of the block was hit
            dx = -dx;
        }
        // Notify the listeners about the hit event
        this.notifyHit(hitter);
        // Return the new velocity
        return new Velocity(dx, dy);
    }

    /**
     * This method draws the block on a given DrawSurface. It fills the block's
     * shape, and then draws the block's shape.
     *
     * @param d the DrawSurface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(),
                (int) (this.shape.getUpperLeft().getY()),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(),
                (int) (this.shape.getUpperLeft().getY()),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * Sets the shape of the block.
     *
     * @param shape the new shape of the block.
     */
    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    /**
     * Sets the color of the block.
     *
     * @param color the new color of the block.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Return the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return color;
    }

    /**
     * This method generates an ArrayList of random blocks to be used in a game,
     * with the specified number of blocks in every row, specified number of rows,
     * block size and the x and y coordinates of the new block upper left point.
     * It starts by creating a new block with a random color and setting its
     * shape to a new rectangle with the specified width and height. It then
     * generates blocks for each row, decrementing the number of blocks in each
     * subsequent row. The blocks are created with the same size and color as the
     * initial block, with the new block's upper-left corner shifted to the left
     * of the previous block's corner by the block width.
     *
     * @param linesAmount  the number of rows of blocks to create
     * @param blocksAmount the number of blocks to create
     * @param width        the width of each block
     * @param height       the height of each block
     * @param x            the x coordinate of the new block upper left point.
     * @param y            the y coordinate of the new block upper left point.
     * @return an ArrayList of generated blocks, including borders for the game
     * screen
     */
    public static ArrayList<Block> randomBlocks(int linesAmount, int width,
                                                int height, int blocksAmount,
                                                double x, double y) {
        ArrayList<Block> blocks = new ArrayList<>();
        // create a new Objects.Block object with a random color
        Block newBlock = new Block(Rectangle.newRec(width, height, x, y),
                Ball.randomColor(Color.cyan));
        newBlock.setColor(newBlock.colorsPalette[0]);
        Rectangle newRec = Rectangle.newRec(width, height, x, y);
        Point newUpperLeft;
        for (int i = 0; i < linesAmount; i++) {
            // create the blocks in each line
            newBlock.setShape(newRec);
            newBlock.setColor(newBlock.colorsPalette[i]);
            for (int j = 0; j < blocksAmount; j++) {
                /*
                 * if the method needs to create just one line of blocks, it changes
                 * the color of the created blocks every after creating 2 blocks
                 */
                if (linesAmount <= 1) {
                    blocks.add(new Block(newBlock.shape.getUpperLeft(),
                            newBlock.shape.getWidth(), newBlock.shape.getHeight(),
                            newBlock.colorsPalette[j / HALF]));
                } else {
                    blocks.add(new Block(newBlock.shape.getUpperLeft(),
                            newBlock.shape.getWidth(), newBlock.shape.getHeight(),
                            newBlock.getColor()));
                }
                newRec = newBlock.shape;
                /*
                 * set the shape of the newBlock to the previous shape, shifted
                 * left by 'width'
                 */
                newUpperLeft = new Point(newRec.getUpperLeft().getX()
                        - width, newRec.getUpperLeft().getY());
                newRec.setUpperLeft(newUpperLeft);
                newBlock.setShape(newRec);
            }
            /*
             * set the shape of the newBlock to the previous shape, shifted
             * down by 'height'
             */
            newRec = newBlock.shape;
            newUpperLeft = new Point(MOST_UPPER_RIGHT_BLOCK_X,
                    newRec.getUpperLeft().getY() + height);
            newRec.setUpperLeft(newUpperLeft);
            newBlock.setShape(newRec);
            blocksAmount--;
        }
        return blocks;
    }

    /**
     * Removes the block from the game by unregistering it from the game's
     * sprite and collidable lists.
     *
     * @param gameLevel the game from which the block should be removed
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * This method then adds three blocks to create borders around the game
     * screen. Finally, it returns the ArrayList of those blocks.
     *
     * @return the borders blocks of the screen.
     */
    public static ArrayList<Block> frameBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        // add the boundary blocks
        blocks.add(new Block(new Point(X_ORIGIN, Y_ORIGIN), BOTTOM_SCREEN,
                LEFT_BOUND_WIDTH, Color.gray));
        blocks.add(new Block(new Point(X_ORIGIN, Y_ORIGIN),
                LEFT_BOUND_WIDTH, BOTTOM_SCREEN, Color.gray));
        blocks.add(new Block(new Point(RIGHT_BOUND_WIDTH, Y_ORIGIN),
                LEFT_BOUND_WIDTH, BOTTOM_SCREEN, Color.gray));
        return blocks;
    }

    /**
     * Adds a HitListener to the list of listeners for this block.
     *
     * @param hl the HitListener to be added
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a HitListener to the list of listeners for this block.
     *
     * @param hl the HitListener to be removed
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered HitListeners about a hit event.
     *
     * @param hitter the Ball object that caused the hit event
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
