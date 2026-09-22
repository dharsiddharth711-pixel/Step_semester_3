package practice_assignment;

import java.util.Scanner;

public class PhoneMasker {

    public static String maskPhoneNumber(String phone) {
        if (phone == null) {
            return "Invalid phone number";
        }

        String trimmed = phone.trim();

        // Validate exact 10 digits
        if (trimmed.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < trimmed.length(); i++) {
            if (!Character.isDigit(trimmed.charAt(i))) {
                return "Invalid phone number";
            }
        }

        // Build masked string using StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append("-");
        sb.append(trimmed.substring(6));

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.println(maskPhoneNumber(phone));
        scanner.close();
    }
}