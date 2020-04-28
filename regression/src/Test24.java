import josx.platform.rcx.*;

public class Test24 {
    public static void main(String[] arg) {
        LCD.showNumber(Battery.getVoltageMilliVolt()/*.getBatteryPower()*/);
        for (; ; ) {
        }
    }
}
