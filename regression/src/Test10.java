
// Throw exception inside synchronized block

import josx.platform.rcx.LCD;
import josx.platform.rcx.LCDConstants;

public class Test10 {
    private static Object MONITOR = new Object();

    public synchronized void method_A()
            throws Exception {
        throw new Exception();
    }

    public static void main(String[] aArg)
            throws Exception {
        new Thread() {
            public void run() {
                final Test10 obj = new Test10();
                Exception k = null;
                try {
                    obj.method_A();
                } catch (Exception e) {
                    k = e;
                }
                final Exception fk = k;
                new Thread() {
                    public void run() {
                        try {
                            obj.method_A();
                        } catch (Exception e) {
                            // ignore
                        }
                        LCD.setNumber(LCDConstants.LCD_UNSIGNED, (short) (fk != null ?
                                4490 : 4491), LCDConstants.LCD_DECIMAL_0);
                        LCD.refresh();
                    }
                }.start();
            }
        }.start();
        for (int i = 0; i < 100000; i++) {
        }
    }
}



