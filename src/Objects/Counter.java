// 206132284 Itay Alter
package Objects;

/**
 * The Counter class represents a simple counter that can be incremented or
 * decremented. It stores an integer value and provides methods for manipulating
 * and accessing this value.
 */
public class Counter {
    private int counter;

    /**
     * Constructs a Counter object with the specified initial value.
     *
     * @param counter the initial value of the counter
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * Constructs a Counter object with an initial value of zero.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the value to increase the counter by
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Increases the counter by one.
     */
    public void increase() {
        this.counter++;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the value to decrease the counter by
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Decreases the counter by one.
     */
    public void decrease() {
        this.counter--;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    int getValue() {
        return this.counter;
    }
}
