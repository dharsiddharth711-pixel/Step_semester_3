package practice_assignment;

interface Auditable {
    String auditRecord();
}

abstract class StaffMember {
    private double salary;
    protected double bonusRate;

    public StaffMember(double salary) {
        this(salary, 0.10);
    }

    public StaffMember(double salary, double bonusRate) {
        this.salary = salary;
        this.bonusRate = bonusRate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0)
            this.salary = salary;
    }

    public abstract double calculateBonus();
}

class TeamLead extends StaffMember implements Auditable {
    private int teamSize;

    public TeamLead(double salary, int teamSize) {
        super(salary);
        this.teamSize = teamSize;
    }

    public TeamLead(double salary, double bonusRate, int teamSize) {
        super(salary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize +
                " team members, salary $" + getSalary();
    }
}

class Test3 {

    static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable)
            return ((Auditable) s).auditRecord();

        return "No audit required";
    }

    public static void main(String[] args) {
        TeamLead t = new TeamLead(60000, 5);

        System.out.println(t.calculateBonus());

        StaffMember ref = t; // upcasting

        System.out.println(getAuditIfApplicable(ref));
    }
}
