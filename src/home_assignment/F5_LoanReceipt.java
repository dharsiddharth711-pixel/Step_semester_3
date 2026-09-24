package home_assignment;

class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (int i = 0; i < bookIds.length; i++) {
            if (bookIds[i] == null ||
                    !bookIds[i].matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (newId == null || !newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] updated = bookIds.clone();
        updated[index] = newId;

        return new LoanReceipt(memberId, updated);
    }

    static {
        System.out.println("Loan ledger initialized");
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {
            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                referenceOnly + " reference-only | " +
                regular + " regular";
    }
}

final class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

public class F5_LoanReceipt {
    public static void main(String[] args) {
        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),
                null,
                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                LoanReceipt.processNightlyCirculation(receipts)
        );
    }
}
