package practice_assignment;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    static double classAverage(SrmStudent[] students) {
        int sum = 0;
        for (SrmStudent s : students) {
            sum += s.attendance;
        }
        return (double) sum / students.length;
    }
}

public class Main1 {
    public static void main(String[] args) {
        SrmStudent[] students = {
                new SrmStudent("Ravi", "RA101", 82),
                new SrmStudent("Anitha", "RA102", 68),
                new SrmStudent("Karthik", "RA103", 91),
                new SrmStudent("Meera", "RA104", 74),
                new SrmStudent("Suresh", "RA105", 60)
        };

        for (SrmStudent s : students) {
            System.out.println(s.name + " - " + s.attendance + "% - " +
                    (s.isEligible() ? "Eligible" : "Detained"));
        }

        System.out.println("Class average: " +
                SrmStudent.classAverage(students) + "%");
    }
}