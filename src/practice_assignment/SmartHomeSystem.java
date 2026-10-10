package practice_assignment;

import java.util.*;

// Capability Interfaces
interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitorable {
    void showEnergy();
}

// Base Device Class
abstract class Device {
    protected String id;
    protected boolean isOn = false;

    public Device(String id) {
        this.id = id;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(id + " is ON");
    }

    public String getId() {
        return id;
    }
}

// Device Implementations
class Light extends Device implements Dimmable, Schedulable {
    public Light(String id) { super(id); }

    @Override
    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    public Fan(String id) { super(id); }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitorable {
    private int energyKWh;

    public Plug(String id, int energyKWh) {
        super(id);
        this.energyKWh = energyKWh;
    }

    @Override
    public void showEnergy() {
        System.out.println(id + " energy " + energyKWh + " kWh");
    }
}

public class SmartHomeSystem {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();

        // Registering devices from Sample Input
        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        // Processing Commands
        processCommand(devices.get("L1"), "ON", null);
        processCommand(devices.get("L1"), "DIM", 40);
        processCommand(devices.get("F1"), "DIM", 30);
        processCommand(devices.get("F1"), "SCHEDULE", "22:00");
        processCommand(devices.get("P1"), "ENERGY", null);
        processCommand(devices.get("L1"), "ENERGY", null);
    }

    public static void processCommand(Device dev, String command, Object param) {
        if (dev == null) return;

        switch (command) {
            case "ON":
                dev.turnOn();
                break;
            case "DIM":
                if (dev instanceof Dimmable) {
                    ((Dimmable) dev).dim((Integer) param);
                } else {
                    System.out.println(dev.getId() + " rejected: DIM unsupported");
                }
                break;
            case "SCHEDULE":
                if (dev instanceof Schedulable) {
                    ((Schedulable) dev).schedule((String) param);
                } else {
                    System.out.println(dev.getId() + " rejected: SCHEDULE unsupported");
                }
                break;
            case "ENERGY":
                if (dev instanceof EnergyMonitorable) {
                    ((EnergyMonitorable) dev).showEnergy();
                } else {
                    System.out.println(dev.getId() + " rejected: ENERGY unsupported");
                }
                break;
        }
    }
}