
import josx.platform.rcx.*;

public class Test19 {
    public static void main(String[] arg) {
        int pB1;
        int pB2;

        pB1 = Memory.readByte((short) 0xffbb) & 0xFF;
        pB2 = Memory.readByte((short) 0xfd85) & 0xFF;

        Memory.writeByte((short) 0xfd85, (byte) (pB2 | 0x40));
        Memory.writeByte((short) 0xffb9, (byte) (pB2 | 0x40));
        Memory.writeByte((short) 0xfd85, (byte) (pB2 | 0x20));
        Memory.writeByte((short) 0xffb9, (byte) (pB2 | 0x20));

        Memory.writeByte((short) 0xffbb, (byte) (pB1 | 0x20));
        Memory.writeByte((short) 0xffbb, (byte) (pB1 | 0x40));
        Memory.writeByte((short) 0xffbb, (byte) (pB1 & ~0x40));
        for (int i = 0; i < 0x0E; i++) {
            Memory.writeByte((short) 0xffbb, (byte) (pB1 & ~0x20));
            Memory.writeByte((short) 0xfd85, (byte) (pB2 | 0x40));
            Memory.writeByte((short) 0xffb9, (byte) (pB2 | 0x40));
            for (int j = 0; j < 8; j++) {
                Memory.writeByte((short) 0xffbb, (byte) (pB1 & ~0x20));
                Memory.writeByte((short) 0xffbb, (byte) (pB1 & ~0x40));
                Memory.writeByte((short) 0xffbb, (byte) (pB1 | 0x20));
            }
            Memory.writeByte((short) 0xffbb, (byte) (pB1 | 0x20));
            Memory.writeByte((short) 0xffbb, (byte) (pB1 & ~0x20));
            Memory.writeByte((short) 0xfd85, (byte) (pB2 & ~0x40));
            Memory.writeByte((short) 0xffb9, (byte) (pB2 & ~0x40));
            Memory.writeByte((short) 0xffbb, (byte) (pB1 | 0x20));
        }
        Memory.writeByte((short) 0xffbb, (byte) (pB1 & ~0x20));
        Memory.writeByte((short) 0xfd85, (byte) (pB2 | 0x40));
        Memory.writeByte((short) 0xffb9, (byte) (pB2 | 0x40));
        Memory.writeByte((short) 0xffbb, (byte) (pB1 & ~0x40));
        Memory.writeByte((short) 0xffbb, (byte) (pB1 | 0x20));
        Memory.writeByte((short) 0xffbb, (byte) (pB1 | 0x40));
        for (int k = 0; k < 1000000; k++) {
        }
    }
}
