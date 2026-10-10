package home_assignment;

import java.util.*;

interface PricingPlan {
    double applyDiscount(double originalPrice);
}

class DayScholarPlan implements PricingPlan {
    @Override public double applyDiscount(double originalPrice) { return originalPrice; }
}

class HostellerPlan implements PricingPlan {
    @Override public double applyDiscount(double originalPrice) { return originalPrice * 0.90; }
}

class StaffPlan implements PricingPlan {
    @Override public double applyDiscount(double originalPrice) { return originalPrice * 0.80; }
}

class Transaction {
    private String description;
    private double amount; // Positive for top-up/refund, negative for purchase

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() { return description; }
    public double getAmount() { return amount; }
}

class SmartCard {
    private String cardId;
    private PricingPlan plan;
    private boolean isBlocked = false;
    private List<Transaction> transactions = new ArrayList<>();
    private Set<String> refundedItems = new HashSet<>();

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    public double getBalance() {
        double sum = 0;
        for (Transaction t : transactions) {
            sum += t.getAmount();
        }
        return sum;
    }

    public void topUp(double amount) {
        if (isBlocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }
        if (amount < 100) {
            System.out.println("Top-up rejected: Minimum top-up is ₹100.");
            return;
        }
        if (getBalance() + amount > 5000) {
            System.out.println("Top-up rejected: Maximum balance limit exceeded.");
            return;
        }

        transactions.add(new Transaction("Top-up", amount));
        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n", cardId, amount, getBalance());
    }

    public void purchase(String itemName, double originalPrice) {
        if (isBlocked) {
            System.out.println("Purchase rejected: Card is blocked.");
            return;
        }

        double finalPrice = plan.applyDiscount(originalPrice);
        if (getBalance() < finalPrice) {
            System.out.printf("Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n", finalPrice, getBalance());
            return;
        }

        transactions.add(new Transaction(itemName, -finalPrice));
        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f.%n", itemName, finalPrice, getBalance());
    }

    public void refund(String itemName, double originalPrice) {
        if (isBlocked) {
            System.out.println("Refund rejected: Card is blocked.");
            return;
        }
        if (refundedItems.contains(itemName)) {
            System.out.println("Refund rejected: " + itemName + " has already been refunded.");
            return;
        }

        double refundAmount = plan.applyDiscount(originalPrice);
        transactions.add(new Transaction("Refund - " + itemName, refundAmount));
        refundedItems.add(itemName);
        System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n", refundAmount, itemName, getBalance());
    }

    public void printMiniStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mini-statement for ").append(cardId).append(": ");
        for (int i = 0; i < transactions.size(); i++) {
            double amt = transactions.get(i).getAmount();
            if (amt > 0) {
                sb.append("+").append(String.format("%.2f", amt));
            } else {
                sb.append(String.format("%.2f", amt));
            }
            if (i < transactions.size() - 1) sb.append(", ");
        }
        sb.append(" = ₹").append(String.format("%.2f", getBalance()));
        System.out.println(sb.toString());
    }
}

public class SmartCardApp {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500.0);
        card.purchase("Veg Thali", 120.0);
        card.purchase("Cold Coffee", 60.0);
        card.purchase("Bulk Items", 400.0);
        card.refund("Veg Thali", 120.0);
        card.refund("Veg Thali", 120.0);
        card.printMiniStatement();
    }
}