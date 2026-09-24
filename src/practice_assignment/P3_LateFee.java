package practice_assignment;

import java.util.Arrays;

class EventTicket3 {
    protected double basePrice;
    protected double amountPaid;

    private final double[] lateFeeHistory = new double[10];
    private int lateFeeCount;

    public EventTicket3(double basePrice) {
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {
        basePrice += amount;
        lateFeeHistory[lateFeeCount++] = amount;
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class WorkshopTicket3 extends EventTicket3 {
    public WorkshopTicket3(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}
