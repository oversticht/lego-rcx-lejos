package ukcrobots.core;

/**
 * A delegating wrapper around the InputDevice class.
 * All methods delegate to the associated device.
 * Multiple sensors may be connected to the same device,
 * if required. However, there are drawbacks to this approach and
 * the most sensible configuration would involve sensors of
 * the same type.
 * This class is based on josx.platform.rcx.Sensor.
 *
 * @version 2001.12.20
 */
public abstract class Sensor {
    private final InputDevice device;

    /**
     * Sets the sensor's mode and type.
     *
     * @param aType 0 = RAW, 1 = TOUCH, 2 = TEMP, 3 = LIGHT, 4 = ROT.
     * @param aMode 0x00 = RAW, 0x20 = BOOL, 0x40 = EDGE, 0x60 = PULSE,
     *              0x80 = PERCENT, 0xA0 = DEGC, 0xC0 = DEGF, 0xE0 = ANGLE.
     *              Also, mode can be OR'd with slope (0..31).
     * @see josx.platform.rcx.SensorConstants
     */
    public Sensor(int id, int type, int mode) throws NoSuchDeviceException {
        device = InputDevice.getDevice(id);
        if (device == null) {
            throw new NoSuchDeviceException();
        }
        device.setTypeAndMode(type, mode);
    }

    /**
     * Reads the canonical value of the sensor.
     */
    public int readValue() {
        return device.readValue();
    }

    /**
     * Adds a sensor listener.
     * In practice, this listener is attached to the underlying
     * device. If different types of sensor are attached to the
     * the same device, then separate listeners could receive
     * confusing notifications.
     */
    public void addSensorListener(SensorListener listener) {
        device.addDeviceListener(listener);
    }

    /**
     * Remove the attached listener.
     */
    public void removeSensorListener(SensorListener listener) {
        device.removeDeviceListener(listener);
    }

    /**
     * Activates the sensor. This method should be called
     * if you want to get accurate values from the
     * sensor. In the case of light sensors, you should see
     * the led go on when you call this method.
     */
    public void activate() {
        device.activate();
    }

    /**
     * Passivates the sensor.
     */
    public final void passivate() {
        device.passivate();
    }

    /**
     * Obtain access to the attached InputDevice in order to
     * provide enhanced functionality via a sub class.
     *
     * @return The associated InputDevice.
     */
    protected InputDevice getDevice() {
        return device;
    }
}
