
import josx.platform.rcx.*;

public class Test18 {
    public static void main(String[] arg) {
        Sensor.S3.activate();
        Sensor.S3.setTypeAndMode(SensorConstants.SENSOR_TYPE_LIGHT, SensorConstants.SENSOR_MODE_PCT);
        Sensor.S3.addSensorListener(
                new SensorListener() {
                    public void stateChanged(Sensor aSource, int aOldValue, int aNewValue) {
                        int pct = aSource.readValue();
                        LCD.showNumber(pct);
                        for (int k = 0; k < 10; k++) {
                        }
                    }
                }
        );
    }
}
