package home_assignment;

public class AtmPinValidator {

    public static void main(String[] args) {
        System.out.println("--- Test 1 ---");
        checkPinLength("482");

        System.out.println("\n--- Test 2 ---");
        checkPinLength("4820");
    }

    /**
     * Validates if the given PIN string is exactly 4 digits long.
     *
     * @param pin The PIN string to validate
     */
    public static void checkPinLength(String pin) {
        try {
            if (pin == null) {
                System.out.println("Invalid PIN — PIN cannot be null.");
                return;
            }

            // Get length using length() method
            int pinLength = pin.length();

            // Perform basic single if/else check
            if (pinLength != 4) {
                System.out.println("Invalid PIN — must be exactly 4 digits.");
            } else {
                System.out.println("PIN length OK.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred during PIN validation: " + e.getMessage());
        }
    }
}