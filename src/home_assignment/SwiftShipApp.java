package home_assignment;

import java.util.*;

enum Status { BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED }

interface NotificationChannel {
    void notify(String parcelId, Status status);
}

class SmsChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, Status status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, Status status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

abstract class ShippingType {
    public abstract double calculateCharge(double weight);
}

class StandardShipping extends ShippingType {
    @Override
    public double calculateCharge(double weight) {
        return 40.0 + (10.0 * weight);
    }
}

class ExpressShipping extends ShippingType {
    @Override
    public double calculateCharge(double weight) {
        return 80.0 + (15.0 * weight);
    }
}

class FragileShipping extends ShippingType {
    private StandardShipping baseShipping = new StandardShipping();

    @Override
    public double calculateCharge(double weight) {
        return baseShipping.calculateCharge(weight) + 50.0;
    }
}

class Parcel {
    private String id;
    private double weight;
    private ShippingType shippingType;
    private Status status = Status.BOOKED;
    private List<NotificationChannel> channels = new ArrayList<>();

    public Parcel(String id, double weight, ShippingType shippingType, String typeName) {
        this.id = id;
        this.weight = weight;
        this.shippingType = shippingType;
        double charge = shippingType.calculateCharge(weight);
        System.out.printf("Parcel %s booked (%s, %.0f kg). Charge: ₹%.2f.%n", id, typeName, weight, charge);
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
        channel.notify(id, status);
    }

    public void updateStatus(Status newStatus) {
        if (newStatus.ordinal() != status.ordinal() + 1) {
            System.out.println("Invalid transition: " + status + " → " + newStatus + " is not allowed.");
            return;
        }
        this.status = newStatus;
        notifyAllChannels();
    }

    public void cancel() {
        if (status != Status.BOOKED) {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
            return;
        }
        System.out.println("Parcel " + id + " cancelled.");
    }

    private void notifyAllChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }
}

public class SwiftShipApp {
    public static void main(String[] args) {
        Parcel p101 = new Parcel("P101", 2.0, new ExpressShipping(), "Express");

        p101.subscribe(new SmsChannel());
        p101.subscribe(new EmailChannel());

        p101.updateStatus(Status.PICKED_UP);
        p101.cancel();
        p101.updateStatus(Status.IN_TRANSIT);
        p101.updateStatus(Status.DELIVERED); // Invalid skip transition
    }
}
