package ukcrobots.core;

/**
 * General event listener.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
public interface SensorListener {
    /**
     * Called when the canonical value of a sensor changes.
     *
     * @param oldValue The old sensor value.
     * @param newValue The new sensor value.
     */
    public void stateChanged(int oldValue, int newValue);
}
