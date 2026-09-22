package home_assignment;

public class IsbnNormalizerValidator {

    public static void main(String[] args) {
        System.out.println("--- Test 1 ---");
        processIsbnCode(" pen2026004251 ");

        System.out.println("\n--- Test 2 ---");
        processIsbnCode("12N2026004251");
    }

    /**
     * Normalizes a raw scanned code: trims spaces and uppercases the first 3 characters.
     */
    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }

        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed;
        }

        String publisherCode = trimmed.substring(0, 3).toUpperCase();
        String remainingCode = trimmed.substring(3);

        return publisherCode + remainingCode;
    }

    /**
     * Validates the normalized code and produces a formatted display string or error message.
     */
    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            return "Invalid: wrong length";
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Validate remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(pubCode).append("] ");
        formatted.append("YEAR: ").append(year);
        formatted.append(" | CATALOG: ").append(catalog);

        return formatted.toString();
    }

    /**
     * Helper pipeline function to normalize and validate in sequence.
     */
    public static void processIsbnCode(String raw) {
        String normalized = normalizeCode(raw);
        String result = validateAndFormat(normalized);
        System.out.println(result);
    }
}
