package practice_assignment;

class FeeAccountF5 {
    private double totalFee;
    private double amountPaid;

    FeeAccountF5(double totalFee, double amountPaid) {
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
        else
            System.out.println("Payment rejected");
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccountF5 extends FeeAccountF5 {
    HostelFeeAccountF5(double totalFee, double amountPaid) {
        super(totalFee, amountPaid);
    }
}

class HostelRoomF5 {
    String roomNo;

    HostelRoomF5(String roomNo) {
        this.roomNo = roomNo;
    }
}

class SrmStudentF5 {
    String name;
    String regNo;
    HostelFeeAccountF5 feeAccount;
    HostelRoomF5 room;

    static int totalStudents = 0;

    SrmStudentF5(String name, String regNo,
               HostelFeeAccountF5 feeAccount,
               HostelRoomF5 room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }

    void fullStatus() {
        System.out.println(name +
                " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " +
                (room == null ? "unallotted" : room.roomNo));
    }
}

public class Main5 {
    public static void main(String[] args) {

        HostelRoomF5 r1 = new HostelRoomF5("C-214");
        HostelRoomF5 r2 = new HostelRoomF5("C-507");

        HostelFeeAccountF5 f1 =
                new HostelFeeAccountF5(200000, 60000);

        HostelFeeAccountF5 f2 =
                new HostelFeeAccountF5(180000, 0);

        HostelFeeAccountF5 f3 =
                new HostelFeeAccountF5(200000, 0);

        f3.pay(-5000);

        SrmStudentF5 s1 =
                new SrmStudentF5("Ravi",
                        "RA101", f1, r1);

        SrmStudentF5 s2 =
                new SrmStudentF5("Anitha",
                        "RA102", f2, r2);

        SrmStudentF5 s3 =
                new SrmStudentF5("Karthik",
                        "RA103", f3, null);

        s1.fullStatus();
        s2.fullStatus();
        s3.fullStatus();

        System.out.println("Total students: "
                + SrmStudentF5.totalStudents);
    }
}