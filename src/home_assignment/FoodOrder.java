package home_assignment;

public class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean isDelivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() ||
                dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name and dish name cannot be blank.");
        }
        this.studentName = studentName;
        this.dishName = dishName;
        this.isDelivered = false;
    }

    public void markDelivered() {
        if (isDelivered) {
            System.out.println("Alert: Order has already been marked as delivered!");
        } else {
            isDelivered = true;
            System.out.println("Order delivered successfully.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int validCount = 0;
        int rejectedCount = 0;

        for (String[] order : rawOrders) {
            if (order == null || order.length < 2) {
                rejectedCount++;
                continue;
            }
            try {
                new FoodOrder(order[0], order[1]);
                validCount++;
            } catch (IllegalArgumentException e) {
                rejectedCount++;
            }
        }
        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }
}