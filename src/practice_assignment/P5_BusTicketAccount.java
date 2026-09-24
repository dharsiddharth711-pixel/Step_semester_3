package practice_assignment;

class BusTicketAccount {
    static String systemName;

    String bookingId;
    double ticketFare;

    static {
        systemName = "Nightly Fleet Reconciliation";
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0) {
            throw new IllegalArgumentException("Invalid ticket fare");
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Invalid delay");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penaltyPercent;

        if (minutesLate <= 5) {
            penaltyPercent = minutesLate * 0.5;
        } else if (minutesLate <= 15) {
            penaltyPercent = 2.5 + (minutesLate - 5);
        } else {
            penaltyPercent = 12.5 + (minutesLate - 15) * 2;
        }

        return ticketFare * penaltyPercent / 100;
    }
}

class SleeperBusTicketAccount extends BusTicketAccount {
    public SleeperBusTicketAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

class FleetProcessor {
    void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return;
        }

        double penalty = account.calculatePenalty(minutesLate);

        if (account instanceof SleeperBusTicketAccount) {
            penalty = penalty * 0.5;
            System.out.println("Sleeper: " + account.bookingId +
                    " | Penalty: " + penalty);
        } else {
            System.out.println("Regular: " + account.bookingId +
                    " | Penalty: " + penalty);
        }
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        if (accounts.length != amounts.length ||
                accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Mismatched array lengths");
        }

        FleetProcessor processor = new FleetProcessor();

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double grandTotal = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double penalty = accounts[i].calculatePenalty(minutesLateArray[i]);

            if (accounts[i] instanceof SleeperBusTicketAccount) {
                penalty = penalty * 0.5;
                sleeper++;
            } else {
                regular++;
            }

            processor.processAccount(
                    accounts[i],
                    amounts[i],
                    minutesLateArray[i]
            );

            grandTotal += penalty;
            processed++;
        }

        System.out.println(
                processed + " processed | " +
                        nullSkipped + " null skipped | " +
                        sleeper + " sleeper | " +
                        regular + " regular | " +
                        "grand total penalties = " + grandTotal
        );
    }
}

public class P5_BusTicketAccount {
    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
                new SleeperBusTicketAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
                1200,
                900,
                700
        };

        int[] minutesLateArray = {
                10,
                5,
                0
        };

        FleetProcessor.processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}
