package practice_assignment;

class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || medicationCodes == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (int i = 0; i < medicationCodes.length; i++) {
            if (medicationCodes[i] == null ||
                    !medicationCodes[i].matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (newCode == null || !newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("Invalid medication code");
        }

        String[] updated = medicationCodes.clone();
        updated[index] = newCode;

        return new DischargeSummary(patientId, updated);
    }

    static {
        System.out.println("Discharge ledger initialized");
    }

    static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (int i = 0; i < summaries.length; i++) {
            if (summaries[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summaries[i] instanceof CriticalCareDischargeSummary) {
                critical++;
            } else {
                routine++;
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                critical + " critical-care | " +
                routine + " routine";
    }
}

final class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);

        if (icuDays < 0) {
            throw new IllegalArgumentException("Invalid ICU days");
        }

        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}

public class P5_DischargeSummary {
    public static void main(String[] args) {
        DischargeSummary[] summaries = {
                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"},
                        4
                ),
                null,
                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"}
                )
        };

        System.out.println(
                DischargeSummary.processNightlyBatch(summaries)
        );
    }
}
