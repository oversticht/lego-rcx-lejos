
// Sensor test

import josx.platform.rcx.*;

public class Test16 {
    private static final Test16 S1 = new Test16();
    private int i = 0;

    static class Inner {
        private static final Object MONITOR = new Object();
    }

    final int readByte0() {
        return 0;
    }

    public static int retvalue() {
        synchronized (Inner.MONITOR) {
            int b1 = S1.readByte0();
            int b2 = 80;
            return (b1 << 8) + b2;
        }
    }

    public static void main(String[] argv) {
        for (int i = 0; i < 50; i++) {
            int pValue = retvalue();
            LCD.setNumber(LCDConstants.LCD_UNSIGNED, pValue,
                    LCDConstants.LCD_DECIMAL_0);
            LCD.setNumber(LCDConstants.LCD_PROGRAM, i % 10, (short) 0);
            LCD.refresh();
            for (int k = 0; k < 1000; k++) {
            }
        }
    }
}
