package ukcrobots.core;


/**
 * Notify a listener when a device changes state.
 * This thread should block until a change occurs.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.21
 */
class ListenerNotifier extends Thread {
    private final SensorListener listener;
    private final ValueBuffer buffer;
    private boolean keepRunning = true;

    /**
     * Create a notifier for a listener.
     *
     * @param listener The listener to be notified of state changes.
     * @param buffer   The buffer used to synchronize on.
     */
    public ListenerNotifier(SensorListener listener, ValueBuffer buffer) {
        this.listener = listener;
        this.buffer = buffer;
    }

    /**
     * Run waiting for a change of state in a device.
     * Notify a listener when this happens. Execution
     * may be terminated by calling terminate.
     *
     * @see #terminate()
     */
    public void run() {
        final long SLEEP_TIME = 100;
        final int[] valuePair = new int[2];
        while (keepRunning) {
            buffer.waitForStateChange(valuePair);
            if (keepRunning) {
                listener.stateChanged(valuePair[0], valuePair[1]);
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    /**
     * Attempt to terminate the notifier thread.
     * As the thread may be waiting on the buffer, it
     * must be released in order to run to completion.
     */
    public void terminate() {
        keepRunning = false;
    }

    /**
     * @return The listener to be notified.
     */
    public SensorListener getListener() {
        return listener;
    }

    /**
     * @return The buffer used for synchronization.
     */
    public ValueBuffer getBuffer() {
        return buffer;
    }
}
