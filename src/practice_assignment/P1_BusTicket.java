package practice_assignment;

import java.util.HashSet;

class BusTicket {
    String passengerName;
    String destination;
    boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination");
        }

        if (!passengerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in: " + passengerName);
        } else {
            System.out.println("Already checked in: " + passengerName);
        }
    }

    static void processBatch(String[][] rawBookings) {
        HashSet<String> acceptedBookings = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (int i = 0; i < rawBookings.length; i++) {
            try {
                BusTicket ticket = new BusTicket(rawBookings[i][0], rawBookings[i][1]);

                String key = ticket.passengerName.toLowerCase() + "|" +
                        ticket.destination.toLowerCase();

                if (acceptedBookings.contains(key)) {
                    duplicates++;
                } else {
                    acceptedBookings.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }
}

public class P1_BusTicket {
    public static void main(String[] args) {
        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        BusTicket.processBatch(bookings);

        BusTicket ticket = new BusTicket("Divya", "Chennai");
        ticket.markCheckedIn();
        ticket.markCheckedIn();
    }
}