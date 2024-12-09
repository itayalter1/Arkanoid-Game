// 206132284 Itay Alter
package Objects;

import java.util.ArrayList;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * This class is responsible for managing a collection of sprites objects. It has
 * methods for adding new sprites, notifying all sprites that time has passed,
 * and drawing all sprites on a given DrawSurface. The class also provides
 * methods for accessing individual sprites in the collection or the entire
 * collection of sprites.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * Add the given sprite to the sprites list.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * This method is used to notify all the sprites in the game that time has
     * passed. It loops through the list of sprites stored in the "sprites"
     * field and calls the timePassed() method of each sprite to update its
     * state according to the elapsed time since the last call.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite: this.sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites on a given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Returns the sprite at the specified index in the list of sprites.
     *
     * @param index the index of the desired sprite
     * @return the sprite at the specified index
     */
    public Sprite getSprite(int index) {
        return this.sprites.get(index);
    }

    /**
     * Returns the list of sprites currently registered to the game.
     *
     * @return The list of sprites currently registered to the game.
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}
