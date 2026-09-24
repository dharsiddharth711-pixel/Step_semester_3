package practice_assignment;

class PatientVitals {
    private double[] readings;
    private int count;

    PatientVitals(double[] initialReadings) {
        readings = new double[500];
        count = 0;

        if (initialReadings != null) {
            for (int i = 0; i < initialReadings.length; i++) {
                recordReading(initialReadings[i]);
            }
        }
    }

    void recordReading(double reading) {
        if (reading <= 0 || reading > 45) {
            return;
        }

        if (count < readings.length) {
            readings[count] = reading;
            count++;
        }
    }

    double getAverage() {
        if (count == 0) {
            return 0;
        }

        double total = 0;

        for (int i = 0; i < count; i++) {
            total += readings[i];
        }

        return total / count;
    }

    double[] getAllReadings() {
        double[] result = new double[count];

        for (int i = 0; i < count; i++) {
            result[i] = readings[i];
        }

        return result;
    }
}

public class P3_PatientVitals {
    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(
                new double[]{36.5, -2, 37.1}
        );

        double[] readings = v.getAllReadings();
        readings[0] = 999;

        double[] result = v.getAllReadings();

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
