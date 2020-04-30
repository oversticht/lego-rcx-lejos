package ukcrobots.core;

import josx.platform.rcx.SensorConstants;

/**
 * Based on josx.platform.rcx.Sensor.
 * Differences between this and the original Sensor class are:
 * <ul>
 *   <li> InputDevice instances are created as required,
 *        and not created statically.
 *   <li> Listeners are handled by a manager.
 * </ul>
 *
 * @version 2001.12.20
 */
class InputDevice {
    public static final int NUM_DEVICES = 3;
    // A shared thread for serving all of the listeners.
    private static final InputDeviceThread INPUT_DEVICE_THREAD =
            new InputDeviceThread();
    /**
     * Array that will contain references to any created input devices [0..2].
     */
    private static final InputDevice[] INPUT_DEVICES =
            new InputDevice[NUM_DEVICES];
    // An identification value for this device. Value of 0, 1, or 2,
    // corresponding to ports 1, 2, or 3 respectively.
    private int id;
    // A manager for this device's listeners.
    private ListenerManager manager;

    /**
     * Create a device.
     *
     * @param id Identify of this device. A value of 0, 1, or 2.
     */
    private InputDevice(int id) {
        this.id = id;
        setTypeAndMode(SensorConstants.SENSOR_TYPE_LIGHT,
                SensorConstants.SENSOR_MODE_PCT);
    }

    /**
     * Return a reference to the InputDevice associated with
     * the input port labelled with the associated id.
     *
     * @param id A value of 1, 2, or 3.
     */
    public synchronized static InputDevice getDevice(int id) {
        int index = id - 1;
        if (index >= 0 && index < INPUT_DEVICES.length) {
            if (INPUT_DEVICES[index] == null) {
                INPUT_DEVICES[index] = new InputDevice(index);
            }
            return INPUT_DEVICES[index];
        } else {
            return null;
        }
    }

    /**
     * <i>Low-level API</i> for reading input device values.
     *
     * @param aCode        InputDevice ID (0..2).
     * @param aRequestType 0 = raw value, 1 = canonical value, 2 = boolean value.
     */
    private static native int readSensorValue(int id, int aRequestType);

    private static native void setSensorValue(int id, int aVal, int aRequestType);

    /**
     * Reads the canonical value of the INPUT_DEVICE.
     */
    public final int readValue() {
        return readSensorValue(id, 1);
    }

    /**
     * Reads the raw value of the INPUT_DEVICE.
     */
    public final int readRawValue() {
        return readSensorValue(id, 0);
    }

    /**
     * Reads the boolean value of the INPUT_DEVICE.
     */
    public final boolean readBooleanValue() {
        return readSensorValue(id, 2) != 0;
    }

    /**
     * Adds a listener to this device.
     * NOTE 1: Calling this method may result in the creation of
     * a non-daemon thread (one for all input devices), i.e. your
     * program will not terminate on its own.<br>
     * NOTE 2: Synchronizing inside listener methods could result
     * in a deadlock.
     *
     * @param listener The device listener.
     */
    public synchronized void addDeviceListener(SensorListener listener) {
        // Hack: Make sure Native is initialized before thread is created.
        Native.getDataAddress(null);
        if (!INPUT_DEVICE_THREAD.isAlive()) {
            INPUT_DEVICE_THREAD.start();
        }
        // Make sure a manager exists for the listeners.
        if (manager == null) {
            manager = new ListenerManager(this);
        }
        // @@@ Should a "previous value" be set for the device reading
        // immediately prior to registering the first listener?
        manager.addDeviceListener(listener);
    }

    /**
     * Remove a listener.
     *
     * @param listener The device listener.
     */
    public synchronized void removeDeviceListener(SensorListener listener) {
        if (manager != null) {
            manager.removeListener(listener);
        }
    }

    /**
     * Activates the input device. This method should be called
     * if you want to get accurate values from the
     * sensor. In the case of light sensors, you should see
     * the LED go on when you call this method.
     */
    public final void activate() {
        Native.call((short) 0x1946, (short) (0x1000 + id));
    }

    /**
     * Passivates the input device.
     */
    public final void passivate() {
        Native.call((short) 0x19C4, (short) (0x1000 + id));
    }

    /**
     * Sets the device's mode and type. If this method isn't called,
     * the default mode is 3 (LIGHT) and the default type is 0x80 (PERCENT).
     *
     * @param aType 0 = RAW, 1 = TOUCH, 2 = TEMP, 3 = LIGHT, 4 = ROT.
     * @param aMode 0x00 = RAW, 0x20 = BOOL, 0x40 = EDGE, 0x60 = PULSE,
     *              0x80 = PERCENT, 0xA0 = DEGC, 0xC0 = DEGF, 0xE0 = ANGLE.
     *              Also, mode can be OR'd with slope (0..31).
     * @see josx.platform.rcx.SensorConstants
     */
    public final void setTypeAndMode(int aType, int aMode) {
        setSensorValue(id, aType, 1);
        setSensorValue(id, aMode, 0);
    }

    /**
     * Resets the canonical device value. This may be useful for rotation devices.
     */
    public final void setPreviousValue(int aValue) {
        setSensorValue(id, aValue, 2);
        INPUT_DEVICE_THREAD.setPreviousValue(id, aValue);
    }

    /**
     * Monitor the active devices for state changes.
     * Have the associated device managers notify listeners
     * of these changes.
     */
    private static class InputDeviceThread extends Thread {
        // Keep previous values for use in the notifications.
        private int[] previousValue = new int[NUM_DEVICES];

        /**
         * Run forever checking the devices.
         */
        public void run() {
            while (true) {
                for (int index = 0; index < NUM_DEVICES; index++) {
                    InputDevice device = INPUT_DEVICES[index];
                    if (device != null) {
                        synchronized (device) {
                            int oldValue = previousValue[index];
                            int newValue = readSensorValue(index, 1);
                            if (oldValue != newValue) {
                                previousValue[index] = newValue;
                                if (device.manager != null) {
                                    device.manager.stateChanged(oldValue, newValue);
                                }
                            }
                        }
                        // @@@ Yielding may be inadequate if listeners are
                        // running at lower-priority.
                        Thread.yield();
                    }
                }
                // @@@ Yielding may be inadequate if listeners are
                // running at lower-priority.
                Thread.yield();
            }
        }

        /**
         * Set a previous value for a device.
         *
         * @param id    Which device.
         * @param value The value to be set.
         */
        public final void setPreviousValue(int id, int value) {
            InputDevice device = INPUT_DEVICES[id];
            if (device != null) {
                synchronized (device) {
                    previousValue[id] = value;
                }
            } else {
                previousValue[id] = value;
            }
        }
    }
}

