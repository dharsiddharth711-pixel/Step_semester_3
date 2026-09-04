package home_assignment;

public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes must be non-negative.");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;
        int remainingDelay = delayMinutes;

        if (remainingDelay > 15) {
            fee += (remainingDelay - 15) * (0.02 * orderValue);
            remainingDelay = 15;
        }
        if (remainingDelay > 5) {
            fee += (remainingDelay - 5) * (0.01 * orderValue);
            remainingDelay = 5;
        }
        if (remainingDelay > 0) {
            fee += remainingDelay * (0.005 * orderValue);
        }

        double minimumFee = orderValue * (minimumSurgePercent / 100.0);
        return Math.max(fee, minimumFee);
    }
}
