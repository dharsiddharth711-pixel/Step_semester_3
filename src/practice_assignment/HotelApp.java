package practice_assignment;

import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomNumber() { return roomNumber; }

    public abstract double calculatePrice(long numDays);
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) { super(roomNumber); }

    @Override
    public double calculatePrice(long numDays) { return numDays * 200.0; }
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) { super(roomNumber); }

    @Override
    public double calculatePrice(long numDays) { return numDays * 150.0; }
}

class Reservation {
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean isCancelled = false;

    public Reservation(Room room, LocalDate checkIn, LocalDate checkOut) {
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Room getRoom() { return room; }
    public boolean isCancelled() { return isCancelled; }
    public void cancel() { this.isCancelled = true; }

    public boolean overlapsWith(LocalDate start, LocalDate end) {
        return !isCancelled && (start.isBefore(checkOut) && end.isAfter(checkIn));
    }
}

class Hotel {
    private Map<String, Room> rooms = new HashMap<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room r) { rooms.put(r.getRoomNumber(), r); }

    public void bookRoom(String roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Room r = rooms.get(roomNumber);
        if (r == null) return;

        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNumber().equals(roomNumber) && res.overlapsWith(checkIn, checkOut)) {
                System.out.println("Booking failed: " + roomNumber + " is not available for " + checkIn + " to " + checkOut + ".");
                return;
            }
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        double total = r.calculatePrice(days);
        Reservation newRes = new Reservation(r, checkIn, checkOut);
        reservations.add(newRes);

        System.out.printf("%s booked from %s to %s. Total price: $%.2f%n", roomNumber, checkIn, checkOut, total);
    }

    public void cancelReservation(String roomNumber) {
        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNumber().equals(roomNumber) && !res.isCancelled()) {
                res.cancel();
                System.out.println("Reservation for " + roomNumber + " cancelled successfully.");
                return;
            }
        }
    }
}

public class HotelApp {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.addRoom(new DeluxeRoom("Deluxe Room 101"));
        hotel.addRoom(new StandardRoom("Standard Room 205"));

        hotel.bookRoom("Deluxe Room 101", LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5));
        hotel.bookRoom("Standard Room 205", LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));
        hotel.bookRoom("Deluxe Room 101", LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));
        hotel.cancelReservation("Deluxe Room 101");
    }
}
