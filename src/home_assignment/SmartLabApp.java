package home_assignment;

import java.util.*;

abstract class Capability {
    public abstract String getName();
    public abstract boolean applyAction(Object value);
}

class PowerCapability extends Capability {
    private boolean isOn = false;

    @Override
    public String getName() { return "Power"; }

    @Override
    public boolean applyAction(Object value) {
        if (value instanceof String) {
            String val = ((String) value).toUpperCase();
            if (val.equals("ON") || val.equals("OFF")) {
                this.isOn = val.equals("ON");
                return true;
            }
        }
        return false;
    }

    public boolean isOn() { return isOn; }
}

class BrightnessCapability extends Capability {
    private int brightness = 0;

    @Override
    public String getName() { return "Brightness"; }

    @Override
    public boolean applyAction(Object value) {
        if (value instanceof Integer) {
            int level = (Integer) value;
            if (level >= 0 && level <= 100) {
                this.brightness = level;
                return true;
            }
        }
        return false;
    }
}

class TemperatureCapability extends Capability {
    private int temp = 20;

    @Override
    public String getName() { return "Temperature"; }

    @Override
    public boolean applyAction(Object value) {
        if (value instanceof Integer) {
            int t = (Integer) value;
            if (t >= 16 && t <= 30) {
                this.temp = t;
                return true;
            }
        }
        return false;
    }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new LinkedHashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName().toLowerCase(), capability);
    }

    public boolean hasCapability(String capName) {
        return capabilities.containsKey(capName.toLowerCase());
    }

    public boolean setCapabilityValue(String capName, Object value) {
        Capability cap = capabilities.get(capName.toLowerCase());
        if (cap != null) {
            return cap.applyAction(value);
        }
        return false;
    }
}

class SceneStep {
    private String capabilityName;
    private Object value;
    private String displayMessage;

    public SceneStep(String capabilityName, Object value, String displayMessage) {
        this.capabilityName = capabilityName;
        this.value = value;
        this.displayMessage = displayMessage;
    }

    public String getCapabilityName() { return capabilityName; }
    public Object getValue() { return value; }
    public String getDisplayMessage() { return displayMessage; }
}

public class SmartLabApp {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        // Execute "Lecture Mode" Scene
        System.out.println("Scene 'Lecture Mode' started.");
        int actionsApplied = 0;

        for (Device d : devices) {
            if (d.setCapabilityValue("Power", "ON")) {
                System.out.println(d.getName() + ": ON.");
                actionsApplied++;
            }
        }

        for (Device d : devices) {
            if (d.hasCapability("Brightness") && d.setCapabilityValue("Brightness", 40)) {
                System.out.println(d.getName() + ": brightness set to 40%.");
                actionsApplied++;
            }
        }

        for (Device d : devices) {
            if (d.hasCapability("Temperature") && d.setCapabilityValue("Temperature", 24)) {
                System.out.println(d.getName() + ": temperature set to 24°C.");
                actionsApplied++;
            }
        }

        System.out.println("Scene 'Lecture Mode' completed: " + actionsApplied + " actions applied.");

        // Invalid Temperature setting
        if (!ac.setCapabilityValue("Temperature", 12)) {
            System.out.println("Rejected: Lab AC temperature must be between 16°C and 30°C.");
        }

        // Add Brightness dynamically to Projector
        projector.addCapability(new BrightnessCapability());
        System.out.println("Projector: Brightness capability added.");
        if (projector.setCapabilityValue("Brightness", 70)) {
            System.out.println("Projector: brightness set to 70%.");
        }
    }
}
