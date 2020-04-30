package ukcrobots.core;

/**
 * A general listener only interested in the fact
 * that a sensor's state has changed, not in the
 * values that indicate the change.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.21
 */
public abstract class SensorChangeListener implements SensorListener {
    /**
     * Notify the listener that the sensor's state has changed.
     */
    public abstract void stateChanged();

    /**
     * This method discards the underlying integer values from
     * the sensor as they are not required.
     *
     * @param oldValue The old sensor value.
     * @param newValue The new sensor value.
     */
    public void stateChanged(int oldValue, int newValue) {
        stateChanged();
    }
}
