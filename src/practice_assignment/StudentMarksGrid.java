package practice_assignment;

import java.util.*;

public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};

        Map<String, int[]> studentMarks = new LinkedHashMap<>();
        studentMarks.put("Anu", new int[]{80, 90, 70});
        studentMarks.put("Ravi", new int[]{60, 85}); // Missing Eng
        studentMarks.put("Meena", new int[]{95, 75, 88});

        // Calculate Totals
        StringBuilder totalsOutput = new StringBuilder("Totals ");
        for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
            int sum = 0;
            for (int mark : entry.getValue()) {
                sum += mark;
            }
            totalsOutput.append(entry.getKey()).append(" ").append(sum).append(", ");
        }
        System.out.println(totalsOutput.substring(0, totalsOutput.length() - 2));

        // Find Subject Toppers
        int numSubjects = subjects.length;
        String[] toppers = new String[numSubjects];
        int[] topScores = new int[numSubjects];
        Arrays.fill(topScores, -1);

        for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
            String student = entry.getKey();
            int[] marks = entry.getValue();

            for (int i = 0; i < marks.length; i++) {
                if (marks[i] > topScores[i]) {
                    topScores[i] = marks[i];
                    toppers[i] = student;
                }
            }
        }

        StringBuilder toppersOutput = new StringBuilder("toppers ");
        for (int i = 0; i < numSubjects; i++) {
            if (topScores[i] != -1) {
                toppersOutput.append(subjects[i]).append(" ").append(toppers[i]).append(" ").append(topScores[i]).append(", ");
            }
        }
        System.out.println(toppersOutput.substring(0, toppersOutput.length() - 2));
    }
}