// 206132284 Itay Alter
package Interfaces;

/**
 * The HitNotifier interface represents an object that can notify registered
 * listeners about hit events. Classes that implement this interface can add and
 * remove HitListeners and notify them when hit events occur.
 */
public interface HitNotifier {

    /**
     * Adds a HitListener to the list of listeners to be notified about hit events.
     *
     * @param hl the HitListener to be adde
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners to no longer be notified
     * about hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
