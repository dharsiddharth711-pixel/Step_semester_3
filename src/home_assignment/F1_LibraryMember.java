package home_assignment;

class AccessChecker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {
            int index = -1;

            for (int j = 0; j < modifiers.length; j++) {
                if (modifiers[j].equals(attempts[i][0])) {
                    index = j;
                    break;
                }
            }

            if (classifyAccess(attempts[i][0], attempts[i][1]).equals("ALLOWED")) {
                allowed[index]++;
            } else {
                denied[index]++;
            }
        }

        return "private: " + allowed[0] + " allowed / " + denied[0] +
                " denied | default: " + allowed[1] + " allowed / " + denied[1] +
                " denied | protected: " + allowed[2] + " allowed / " + denied[2] +
                " denied | public: " + allowed[3] + " allowed / " + denied[3] +
                " denied";
    }
}

class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    LibraryMember(String membershipId, String branchCode,
                  double finesOwed, String displayName) {

        String id = membershipId == null ? "" : membershipId.trim();

        if (id.isEmpty() || id.length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.membershipId = id;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class F1_LibraryMember {
    public static void main(String[] args) {
        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeByModifier(attempts));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}
