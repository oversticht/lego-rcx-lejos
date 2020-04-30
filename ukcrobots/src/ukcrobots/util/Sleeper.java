package ukcrobots.util;

/**
 * A utility class to provide a sleep method without having
 * to worry about an InterruptedException.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
public class Sleeper {
    /**
     * Sleep for a non-guaranteed time up to <i>time</i> milliseconds.
     * This method is an exception-catching wrapper around java.lang.Thread.sleep
     * to avoid having to write the exception handler.
     *
     * @param time The maximum time to sleep
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}
