package ukcrobots.core;


/**
 * A delegating wrapper around the OutputDevice class.
 * All methods delegate to the associated device.
 * Multiple motors may be connected to the same device,
 * if required.
 * This class is based on josx.platform.rcx.Motor
 *
 * @version 2001.12.20
 */
public class Motor {
    private final OutputDevice device;

    /**
     * Create a motor.
     *
     * @param id Which output port the motor is attached to.
     *           This should have a value of 'A', 'B', or 'C'.
     * @throws NoSuchDeviceException If the value of id is incorrect.
     */
    public Motor(char id) throws NoSuchDeviceException {
        device = OutputDevice.getDevice(id);
        if (device == null) {
            throw new NoSuchDeviceException();
        }
    }

    /**
     * Causes the device to rotate forward.
     */
    public void forward() {
        device.forward();
    }

    /**
     * Causes the device to rotate backwards.
     */
    public void backward() {
        device.backward();
    }

    /**
     * Reverses direction of the device. It only has
     * effect if the device is moving.
     */
    public void reverse() {
        device.reverse();
    }

    /**
     * Returns the current device power.
     */
    public int getPower() {
        return device.getPower();
    }

    /**
     * Sets the device power to a <i>value between 0 and 7</i>.
     *
     * @param A value in the range [0-7].
     */
    public void setPower(int power) {
        device.setPower(power);
    }

    /**
     * @return true iff the device is currently in motion.
     */
    public boolean isMoving() {
        return device.isMoving();
    }

    /**
     * @return true iff the device is currently in float mode.
     */
    public boolean isFloating() {
        return device.isFloating();
    }

    /**
     * Causes the device to stop, pretty much
     * instantaneously. In other words, the
     * device doesn't just stop; it will resist
     * any further motion.
     */
    public void stop() {
        device.stop();
    }

    /**
     * Causes the device to float. The device will lose all power,
     * but this is not the same as stopping. Use this
     * method if you don't want your robot to trip in
     * abrupt turns.
     */
    public void flt() {
        device.flt();
    }
}







