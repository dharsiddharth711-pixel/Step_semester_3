package practice_assignment;

import java.util.*;

abstract class Vehicle {
    private String id;
    private boolean isAvailable = true;

    public Vehicle(String id) { this.id = id; }

    public String getId() { return id; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {
    public StandardCar(String id) { super(id); }

    @Override
    public double calculateCharge(int days) { return days * 50.0; }
}

class LuxuryCar extends Vehicle {
    public LuxuryCar(String id) { super(id); }

    @Override
    public double calculateCharge(int days) { return days * 100.0; }
}

class RentalService {
    private Map<String, Vehicle> fleet = new HashMap<>();

    public void addVehicle(Vehicle v) { fleet.put(v.getId(), v); }

    public void rentVehicle(String vehicleId, int days) {
        Vehicle v = fleet.get(vehicleId);
        if (v == null || !v.isAvailable()) {
            System.out.println(vehicleId + " is currently unavailable.");
            return;
        }
        v.setAvailable(false);
        double charge = v.calculateCharge(days);
        System.out.printf("%s rented for %d days. Total charge: $%.2f%n", vehicleId, days, charge);
    }

    public void returnVehicle(String vehicleId) {
        Vehicle v = fleet.get(vehicleId);
        if (v != null && !v.isAvailable()) {
            v.setAvailable(true);
            System.out.println(vehicleId + " returned. Now available.");
        }
    }
}

public class RentalApp {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        service.addVehicle(new LuxuryCar("Luxury Car A"));
        service.addVehicle(new StandardCar("Standard Car B"));

        service.rentVehicle("Luxury Car A", 3);
        service.rentVehicle("Standard Car B", 5);
        service.returnVehicle("Luxury Car A");
    }
}
