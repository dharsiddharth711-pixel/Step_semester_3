package home_assignment;
import java.util.Arrays;

class RaceEntry {
    private String bibNumber;
    private double entryFee;
    private double balanceDue;
    private double[] lateFeeHistory;
    private int historyCount;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Bib number must be at least 4 non-whitespace characters.");
        }
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
        this.lateFeeHistory = new double[10];
        this.historyCount = 0;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    protected void applyLateFee(double amount) {
        this.balanceDue += amount;
        if (historyCount < lateFeeHistory.length) {
            lateFeeHistory[historyCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, historyCount);
    }

    public void announce() {
        System.out.println("Race Entry | Bib: " + bibNumber + " | Balance: " + balanceDue);
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

// Single Inheritance Specialization
class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    @Override
    public void announce() {
        System.out.println("Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getBalanceDue());
    }
}