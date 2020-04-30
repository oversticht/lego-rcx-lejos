package ukcrobots.core;

/**
 * Provide a buffer for synchronization of state changes
 * in a device. If a producer produces faster than a consumer
 * is able to consume then unconsumed values are overwritten.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
class ValueBuffer {
    // The values to be passed from producer to consumer.
    private int oldValue, newValue;
    // Whether any new values have been produced.
    private boolean available = false;

    /**
     * Record a change of state in a device.
     * Save the values for the consumer and notify it that
     * they are available.
     *
     * @param oldValue The previous value.
     * @param newValue The new value.
     */
    public synchronized void stateChanged(int oldValue, int newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        available = true;
        notify();
    }

    /**
     * Wait for a state change in a device.
     * Expect a notification in order to be released
     * from the wait.
     *
     * @param valuePair Where to store the old and new
     *                  values when they are available.
     */
    public synchronized void waitForStateChange(int[] valuePair) {
        while (!available()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        valuePair[0] = oldValue;
        valuePair[1] = newValue;
        available = false;
    }

    /**
     * Are there values waiting consumption?
     *
     * @return true if there are values, false otherwise.
     */
    public synchronized boolean available() {
        return available;
    }
}
