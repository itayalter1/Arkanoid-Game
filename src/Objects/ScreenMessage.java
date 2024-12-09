// 206132284 Itay Alter
package Objects;

import java.awt.Color;

/**
 * The ScreenMessage class represents a message displayed on the screen.
 * It consists of a block frame and a message string.
 */
public class ScreenMessage {
    private Block frame;
    private String message;
    static final int START_FRAME_X = 100;
    static final int START_FRAME_Y = 100;
    static final int FRAME_WIDTH = 600;
    static final int FRAME_HEIGHT = 350;
    static final int START_OF_TEXT_X = 115;
    static final int START_OF_TEXT_Y = 250;


    /**
     * Constructs a ScreenMessage object with the given message and frame.
     *
     * @param message the message to be displayed
     * @param frame   the block frame surrounding the message
     */
    public ScreenMessage(String message, Block frame) {
        this.frame = frame;
        this.message = message;
    }

    /**
     * Constructs a ScreenMessage object with the given message and default frame.
     *
     * @param message the message to be displayed
     */
    public ScreenMessage(String message) {
        this.frame = new Block(new Point(START_FRAME_X, START_FRAME_Y),
                FRAME_WIDTH, FRAME_HEIGHT,
                Color.white);
        this.message = message;
    }

    /**
     * Draws the ScreenMessage on the given DrawSurface with the specified font
     * size.
     *
     * @param d    the DrawSurface on which to draw the ScreenMessage
     * @param font the font size for the text
     */
    public void drawOn(biuoop.DrawSurface d, int font) {
        this.frame.drawOn(d);
        d.drawText(START_OF_TEXT_X, START_OF_TEXT_Y, this.message, font);
    }

    /**
     * Draws the message on the given DrawSurface at the specified coordinates
     * with the specified font size. This method first draws the frame on the
     * DrawSurface. Then, it draws the message on the DrawSurface at the given
     * coordinates (x, y) with the specified font size. The message is the text
     * to be displayed.
     *
     * @param d the DrawSurface to draw on
     * @param font the font size of the message
     * @param x the x-coordinate of the message's starting position
     * @param y the y-coordinate of the message's starting position
     */
    public void drawOn(biuoop.DrawSurface d, int font, int x, int y) {
        this.frame.drawOn(d);
        d.drawText(x, y, this.message, font);
    }

    /**
     * Sets the message to be displayed.
     *
     * @param message the message to be set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
