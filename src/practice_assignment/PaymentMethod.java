package practice_assignment;

abstract class PaymentMethod {
    private static int counter = 1000;
    private final String transactionId;

    public PaymentMethod() {
        transactionId = "TXN-" + (++counter);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }
}

class CreditCardPayment extends PaymentMethod {
    private String lastFour;

    public CreditCardPayment(String lastFour) {
        this.lastFour = lastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount +
                " to card ending " + lastFour +
                " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount +
                " in cash - Txn " + getTransactionId();
    }
}

class Test1 {
    static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment cc = new CreditCardPayment("4471");
        CashPayment cash = new CashPayment();

        PaymentMethod ref = cc; // upcasting

        printConfirmation(ref, 250);
        System.out.println(cash.processPayment(40));
        System.out.println(cc.processPayment(250, "Birthday gift"));
    }
}