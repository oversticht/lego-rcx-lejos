package ukcrobots.core;

import josx.platform.rcx.SensorConstants;

/**
 * Model a touch sensor as a device returning boolean values.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
public class TouchSensor extends Sensor {
    public TouchSensor(int id) throws NoSuchDeviceException {
        super(id, SensorConstants.SENSOR_TYPE_TOUCH,
                SensorConstants.SENSOR_MODE_BOOL);
    }

    /**
     * Interprets the value of the sensor as a boolean value
     *
     * @return true if the sensor is pressed, false otherwise.
     */
    public boolean isPressed() {
        return getDevice().readBooleanValue();
    }
}
