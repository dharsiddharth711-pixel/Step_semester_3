package home_assignment;

class EmployeeF5 {
    private double salary;

    EmployeeF5(double salary) {
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployeeF5 extends EmployeeF5 {
    private double bonus;

    ManagerEmployeeF5(double salary, double bonus) {
        super(salary);
        this.bonus = bonus;
    }

    double effectiveSalary() {
        return getSalary() + bonus;
    }
}

class ParkingSlotF5 {
    String slotNo;

    ParkingSlotF5(String slotNo) {
        this.slotNo = slotNo;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    EmployeeF5 employee;
    ParkingSlotF5 slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name,
                          String empId,
                          EmployeeF5 employee,
                          ParkingSlotF5 slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    void fullProfile() {
        double pay;

        if (employee instanceof ManagerEmployeeF5) {
            pay = ((ManagerEmployeeF5) employee)
                    .effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String slotInfo =
                (slot == null)
                        ? "no parking assigned"
                        : slot.slotNo;

        System.out.println(name +
                " | Pay: Rs " + pay +
                " | Slot: " + slotInfo);
    }
}

public class Main5 {
    public static void main(String[] args) {

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E101",
                        new ManagerEmployeeF5(
                                70000, 8000),
                        new ParkingSlotF5("A1"));

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E102",
                        new EmployeeF5(40000),
                        new ParkingSlotF5("A2"));

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E103",
                        new EmployeeF5(10000),
                        null);

        r1.fullProfile();
        r2.fullProfile();
        r3.fullProfile();

        System.out.println(
                "Total records: "
                        + CompanyEmployeeRecord.totalRecords);
    }
}