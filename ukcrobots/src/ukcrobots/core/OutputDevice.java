package ukcrobots.core;

/**
 * Implement singleton instances of the output device ports.
 * The instances are created as required.
 * This class is based on josx.platform.rcx.Motor
 *
 * @version 2001.12.20
 */
class OutputDevice {
    /**
     * An array of the existing output devices.
     */
    private static OutputDevice[] devices = new OutputDevice[3];
    // The identity of this device: 'A', 'B', or 'C'.
    private final char id;
    // The default mode.
    private short mode = 4;
    // The default power.
    private short power = 3;

    /**
     * Create a device instance.
     *
     * @param id Which OutputDevice: 'A', 'B', or 'C'.
     */
    private OutputDevice(char id) {
        this.id = id;
    }

    /**
     * Return the singleton instance of a OutputDevice identified
     * by the parameter.
     *
     * @param id Which OutputDevice: 'A', 'B', or 'C'.
     */
    public synchronized static OutputDevice getDevice(char id) {
        int index = id - 'A';
        if (index >= 0 && index < devices.length) {
            if (devices[index] == null) {
                devices[index] = new OutputDevice(id);
            }
            return devices[index];
        } else {
            return null;
        }
    }

    /**
     * <i>Low-level API</i> for controlling a device.
     * This method is not meant to be called directly.
     * If called, other methods such as isRunning() will
     * be unreliable.
     *
     * @param id    The OutputDevice id: 'A', 'B' or 'C'.
     * @param mode  1=forward, 2=backward, 3=stop, 4=float
     * @param power A value in the range [0-7].
     */
    private static void controlMotor(char id, int mode, int power) {
        Native.call((short) 0x1a4e, (short) (0x2000 + id - 'A'),
                (short) mode, (short) power);
    }

    /**
     * Causes the device to rotate forward.
     */
    public final void forward() {
        mode = 1;
        controlMotor(id, 1, power);
    }

    /**
     * Causes the device to rotate backwards.
     */
    public final void backward() {
        mode = 2;
        controlMotor(id, 2, power);
    }

    /**
     * Reverses direction of the device. It only has
     * effect if the device is moving.
     */
    public final void reverse() {
        if (mode == 1 || mode == 2) {
            mode = (short) (3 - mode);
            controlMotor(id, mode, power);
        }
    }

    /**
     * Returns the current device's power.
     */
    public final int getPower() {
        return power;
    }

    /**
     * Sets OutputDevice power to a <i>value between 0 and 7</i>.
     *
     * @param A value in the range [0-7].
     */
    public final void setPower(int power) {
        this.power = (short) power;
        controlMotor(id, mode, power);
    }

    /**
     * @return true iff the device is currently in motion.
     */
    public final boolean isMoving() {
        return (mode == 1 || mode == 2);
    }

    /**
     * @return true iff the device is currently in float mode.
     */
    public final boolean isFloating() {
        return mode == 4;
    }

    /**
     * Causes the device to stop, pretty much
     * instantaneously. In other words, the
     * device doesn't just stop; it will resist
     * any further motion.
     */
    public final void stop() {
        mode = 3;
        controlMotor(id, mode, 7);
    }

    /**
     * Causes the device to float. The device will lose all power,
     * but this is not the same as stopping. Use this
     * method if you don't want your robot to trip in
     * abrupt turns.
     */
    public final void flt() {
        mode = 4;
        controlMotor(id, mode, power);
    }
}
