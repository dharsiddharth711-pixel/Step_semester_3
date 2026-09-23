package home_assignment;

class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName,
                   double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class Main2 {
    public static void main(String[] args) {

        Employee e =
                new Employee(1, "Rahul", 40000);

        ManagerEmployee m =
                new ManagerEmployee(2, "Amit",
                        70000, 8000);

        InternEmployee i =
                new InternEmployee(3, "Priya",
                        12000, 10000);

        System.out.println("Plain employee pay: Rs "
                + e.getSalary());

        System.out.println("Manager effective pay: Rs "
                + m.effectiveSalary());

        System.out.println("Intern effective pay: Rs "
                + i.effectiveSalary());
    }
}