package practice_assignment;

import java.util.*;

interface IPaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements IPaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment via Credit Card successful.");
        return true;
    }
}

class DigitalWalletPayment implements IPaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment via Digital Wallet failed.");
        return false;
    }
}

class LineItem {
    private String name;
    private int quantity;

    public LineItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() { return name + " (Qty " + quantity + ")"; }
}

class Order {
    private int orderId;
    private List<LineItem> items = new ArrayList<>();
    private String status = "Created";

    public Order(int orderId) { this.orderId = orderId; }

    public void addItem(String name, int qty) { items.add(new LineItem(name, qty)); }

    public void placeOrder(IPaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed.");
        boolean success = paymentMethod.processPayment(100.0);

        if (success) {
            status = "Paid";
            System.out.println("Order status: " + status + ".");
            System.out.println("Notification: Order #" + orderId + " placed and paid.");
        } else {
            status = "Pending Payment";
            System.out.println("Order status: " + status + ".");
            System.out.println("Notification: Order #" + orderId + " placed, awaiting payment.");
        }
    }
}

public class FoodApp {
    public static void main(String[] args) {
        Order order1 = new Order(123);
        order1.addItem("Pizza", 2);
        order1.addItem("Soda", 1);
        System.out.println("Order created. Added Pizza (Qty 2), Soda (Qty 1).");

        Order emptyOrder = new Order(0);
        emptyOrder.placeOrder(new CreditCardPayment());

        order1.placeOrder(new CreditCardPayment());

        Order order2 = new Order(124);
        order2.addItem("Burger", 1);
        order2.placeOrder(new DigitalWalletPayment());
    }
}