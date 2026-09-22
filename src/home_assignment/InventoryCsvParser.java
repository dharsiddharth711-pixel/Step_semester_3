package home_assignment;

public class InventoryCsvParser {

    public static void main(String[] args) {
        System.out.println("--- Test 1 (Valid Record) ---");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");

        System.out.println("\n--- Test 2 (Invalid Record) ---");
        parseInventoryRecord("Wireless Mouse,150");
    }

    /**
     * Parses a CSV line containing product inventory details and displays formatted output.
     *
     * @param csvLine Single CSV record line in format "ProductName,SKU,Quantity"
     */
    public static void parseInventoryRecord(String csvLine) {
        try {
            if (csvLine == null || csvLine.trim().isEmpty()) {
                System.out.println("Invalid Record");
                return;
            }

            // Split CSV record by comma
            String[] fields = csvLine.split(",");

            // Array length validation: must contain exactly 3 fields
            if (fields.length != 3) {
                System.out.println("Invalid Record");
                return;
            }

            String productName = fields[0].trim();
            String sku = fields[1].trim();
            String quantity = fields[2].trim();

            System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + quantity);

        } catch (Exception e) {
            System.out.println("Invalid Record");
        }
    }
}
