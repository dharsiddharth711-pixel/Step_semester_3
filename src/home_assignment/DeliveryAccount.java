package home_assignment;

public class DeliveryAccount {
    protected String studentId;
    protected double orderValue;

    static {
        System.out.println("System Initialized: Loading Nightly Reconciliation Module...");
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes <= 0) return 0.0;
        // Flat-rate 1% per minute calculation for simplicity as requested
        return delayMinutes * 0.01 * orderValue;
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        double fee = account.calculateSurgeFee(delayMinutes);
        // Settlement logic goes here
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delays) {
        if (accounts == null || amounts == null || delays == null) return;

        // Match lengths to avoid disastrous misallocations
        int safeLength = Math.min(accounts.length, Math.min(amounts.length, delays.length));

        int processed = 0, nullSkipped = 0, premium = 0, regular = 0;
        double totalSurgeFees = 0.0;

        for (int i = 0; i < safeLength; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            // Validate premium vs regular processing
            if (accounts[i] instanceof PremiumAccount) {
                premium++;
            } else {
                regular++;
            }

            double fee = accounts[i].calculateSurgeFee(delays[i]);
            totalSurgeFees += fee;
            processed++;
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " +
                premium + " premium | " + regular + " regular | grand total surge fees = Rs " + totalSurgeFees);
    }
}

class PremiumAccount extends DeliveryAccount {
    public PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
    // Premium accounts might override specific non-final methods here
}