package practice_assignment;

interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor {
    private String zoneName;

    public SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor implements Alertable {

    public MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + getZoneName() + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private String secondZone;

    public DualZoneMotionSensor(String zone, String secondZone) {
        super(zone);
        this.secondZone = secondZone;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message)
                + " [also covering " + secondZone + "]";
    }
}

class SmokeDetector implements Alertable {
    private String deviceId;

    public SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}

class Test2 {

    static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable d : devices)
            System.out.println(d.sendAlert(message));
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor)
            return ((MotionSensor) a).getZoneName();

        return "Not a motion sensor";
    }

    public static void main(String[] args) {
        MotionSensor m = new MotionSensor("Living Room");
        SmokeDetector s = new SmokeDetector("SD-01");

        broadcastAll(new Alertable[]{m, s}, "Motion detected");

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));
    }
}