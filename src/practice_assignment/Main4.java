package practice_assignment;

class BrokenStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(String n, String r, int a) {
        name = n;
        regNo = r;
        attendance = a;
    }
}

class SrmStudentF4 {
    String name;
    String regNo;
    int attendance;

    static String university = "SRM University";
    static int admissionCount = 0;

    SrmStudentF4(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;
        regNo = "RA2311003010" +
                String.format("%02d", admissionCount);
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: "
                + admissionCount);
    }
}

public class Main4 {
    public static void main(String[] args) {

        BrokenStudent s1 =
                new BrokenStudent("Ravi", "RA101", 80);

        BrokenStudent s2 =
                new BrokenStudent("Meera", "RA102", 90);

        System.out.println("Broken Version:");
        System.out.println(s1.name);
        System.out.println(s2.name);

        System.out.println("\nFixed Version:");

        SrmStudentF4 st1 =
                new SrmStudentF4("Ravi", 80);

        SrmStudentF4 st2 =
                new SrmStudentF4("Meera", 90);

        st1.printIdCard();
        st2.printIdCard();

        SrmStudentF4.printTotalAdmissions();
    }
}