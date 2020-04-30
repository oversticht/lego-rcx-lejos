package ukcrobots.core;

import josx.platform.rcx.SensorConstants;

/**
 * Model a light sensor.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
public class LightSensor extends Sensor {
    /**
     * Create a light sensor attached to a device.
     *
     * @param id Which device. A value of 1, 2, or 3.
     */
    public LightSensor(int id) throws NoSuchDeviceException {
        super(id, SensorConstants.SENSOR_TYPE_LIGHT,
                SensorConstants.SENSOR_MODE_PCT);
    }
}

