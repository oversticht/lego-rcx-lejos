
// Static initializers

import josx.platform.rcx.Sound;

public class Test08 {
    static {
        Sound.playTone(200, 50);
    }

    static class Inner {
        static {
            Sound.playTone(1000, 50);
        }

        void method() {
            Sound.playTone(2000, 50);
        }
    }

    public static void main(String[] aArg) {
        new Inner().method();
        for (int i = 0; i < 20000; i++) {
        }
    }
}
