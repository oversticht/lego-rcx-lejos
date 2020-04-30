package ukcrobots.core;

/**
 * An abstract class for listeners on Boolean sensors, such as
 * touch sensors.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
public abstract class BooleanSensorListener implements SensorListener {
    /**
     * Notify on a change of device state.
     *
     * @param oldValue The previous value of the device.
     * @param newValue The new value of the device.
     */
    public abstract void stateChanged(boolean oldValue, boolean newValue);

    /**
     * This method maps the underlying integer values from
     * the sensor as booleans.
     *
     * @param oldValue The old sensor value.
     * @param newValue The new sensor value.
     */
    public void stateChanged(int oldValue, int newValue) {
        stateChanged(oldValue != 0, newValue != 0);
    }
}
