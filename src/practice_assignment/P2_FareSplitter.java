package practice_assignment;

class FareSplitter {
    String tripId;
    double totalFare;
    int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    double[] fareBreakdown() {
        double[] shares = new double[passengerCount];

        if (totalFare == 0) {
            return shares;
        }

        double share = Math.floor((totalFare / passengerCount) * 100) / 100;

        for (int i = 0; i < passengerCount - 1; i++) {
            shares[i] = share;
        }

        shares[passengerCount - 1] =
                Math.round((totalFare - share * (passengerCount - 1)) * 100) / 100.0;

        return shares;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class P2_FareSplitter {
    public static void main(String[] args) {
        FareSplitter split1 = new FareSplitter("TRIP001", 100000, 3);
        FareSplitter split2 = new FareSplitter("TRIP003");

        double[] result1 = split1.fareBreakdown();
        double[] result2 = split2.fareBreakdown();

        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + " ");
        }

        System.out.println();
        System.out.println(split1.isConfirmationOverdue(2, 3));
    }
}