package ukcrobots.core;

import josx.platform.rcx.SensorConstants;

/**
 * Model a rotation sensor.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
public class RotationSensor extends Sensor {
    /**
     * Create a rotation sensor.
     *
     * @param id Which input port the sensor is connected to: 1, 2, or 3.
     * @throws NoSuchDeviceException if the device id is not recognised.
     */
    public RotationSensor(int id) throws NoSuchDeviceException {
        super(id, SensorConstants.SENSOR_TYPE_ROT,
                SensorConstants.SENSOR_MODE_ANGLE);
    }

    /**
     * Resets the canonical sensor value.
     */
    public void setPreviousValue(int aValue) {
        getDevice().setPreviousValue(aValue);
    }
}

