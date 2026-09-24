package practice_assignment;

class EventTicket5 {
    protected double basePrice;
    protected double amountPaid;

    public final String ticketId;
    private static int ticketsIssued = 0;

    public EventTicket5(double basePrice) {
        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid price");

        ticketsIssued++;
        ticketId = "TCK-" + (1000 + ticketsIssued);
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'F'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    static int getTicketsIssued() {
        return ticketsIssued;
    }

    static String processNightlySettlement(EventTicket5[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket5 ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket)
                group++;
            else
                individual++;
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }
}

class GroupTicket extends EventTicket5 {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}