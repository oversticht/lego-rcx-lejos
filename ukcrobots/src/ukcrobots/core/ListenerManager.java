package ukcrobots.core;

import ukcrobots.util.ArrayList;

/**
 * Manage a list of listeners on behalf of an InputDevice.
 * When the device notices a change of input value, the
 * listener manager is delegated to the task of informing
 * listeners.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
class ListenerManager {
    // Be conservative and assume there will only be one listener.
    private ArrayList notifiers = new ArrayList(1);

    /**
     * Create a listener manager for the given device.
     *
     * @param device Which device we are managing for.
     */
    public ListenerManager(InputDevice device) {
    }

    /**
     * Adds a listener for the associated device.
     * A ValueBuffer provides synchronization between
     * a notification thread and the device thread.
     *
     * @param listener The listener to be notified of changes.
     */
    public void addDeviceListener(SensorListener listener) {
        ListenerNotifier notifier = new ListenerNotifier(listener,
                new ValueBuffer());
        notifiers.add(notifier);
        notifier.start();
    }

    /**
     * Remove the attached listener.
     *
     * @param listener The listener to be removed.
     */
    public void removeListener(SensorListener listener) {
        final int numNotifiers = notifiers.size();
        for (int i = 0; i < numNotifiers; i++) {
            ListenerNotifier notifier = (ListenerNotifier) notifiers.get(i);
            if (notifier.getListener() == listener) {
                notifier.terminate();
                notifiers.remove(i);
                // @@@ We have a garbage blocked thread.
                // Attempt to wake it up to kill it.
                notifier.getBuffer().notify();
                break;
            }
        }
    }

    /**
     * The state of the device has changed. Notify all
     * of its listeners.
     *
     * @param oldValue The old value of the device.
     * @param newValue The new value of the device.
     */
    public void stateChanged(int oldValue, int newValue) {
        int numNotifiers = notifiers.size();
        for (int i = 0; i < numNotifiers; i++) {
            ListenerNotifier notifier = (ListenerNotifier) notifiers.get(i);
            ValueBuffer buffer = notifier.getBuffer();
            buffer.stateChanged(oldValue, newValue);
        }
    }
}
