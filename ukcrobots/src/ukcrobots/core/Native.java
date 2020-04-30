package ukcrobots.core;

/**
 * Provides access to native routines.
 * Based on josx.platform.rcx.
 * So far, this is unchanged from the original. It is provided
 * in the this package so that the Native methods are
 * available to the InputDevice and OutputDevice classes.
 *
 * @version 2003.09.29
 */
class Native {
    /**
     * Should be used for all native memory accesses.
     *
     * @see josx.platform.rcx.Memory#readByte
     * @see josx.platform.rcx.Memory#writeByte
     */
    public static final Object MEMORY_MONITOR = new Object();

    static final byte[] iAuxData = new byte[7];
    static final int iAuxDataAddr = getDataAddress(iAuxData);

    native static void call(short aAddr);

    native static void call(short aAddr, short a1);

    native static void call(short aAddr, short a1, short a2);

    native static void call(short aAddr, short a1, short a2, short a3);

    native static void call(short aAddr, short a1, short a2, short a3, short a4);

    native static byte readByte(int aAddr);

    native static void writeByte(int aAddr, byte aByte);

    native static int getDataAddress(Object obj);

    native static void setBit(int aAddr, int bit, int value);

    static int readMemoryShort(int aAddr) {
        int b1 = Native.readByte(aAddr) & 0xFF;
        int b2 = Native.readByte(aAddr + 1) & 0xFF;
        return (b1 << 8) + b2;
    }
}


