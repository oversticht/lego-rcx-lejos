package josx.robotics;

/**
 * Useful base class. Makes it less obvious
 * that we happen to be subclassing Thread.
 * In addition if you need an activity that executes whenever a Activity
 * is executed, you can subclass this and waut() on monitor.
 *
 * @author Paul Andrews
 * @see Activity
 */
public abstract class ActivityBase extends Thread {
    protected static final Object monitor = new Object();

    /**
     * Set this thread to be a daemon thread.
     */
    public ActivityBase() {
        setDaemon(true);
    }
}
