package practice_assignment;

import java.util.*;

enum LeaveStatus { PENDING, APPROVED, REJECTED }

class LeaveRequest {
    private String employeeName;
    private String dates;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(String employeeName, String dates) {
        this.employeeName = employeeName;
        this.dates = dates;
        System.out.println("Leave request submitted by " + employeeName + " for " + dates + ". Status: " + status + ".");
    }

    public String getEmployeeName() { return employeeName; }
    public LeaveStatus getStatus() { return status; }

    public void setStatus(LeaveStatus newStatus) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change status: " + status + " request cannot revert to Pending.");
            return;
        }
        this.status = newStatus;
        System.out.println("Leave request for " + employeeName + " " + newStatus.toString().toLowerCase() + ". Status: " + status + ".");
    }
}

public class LeaveApp {
    public static void main(String[] args) {
        LeaveRequest req1 = new LeaveRequest("John Doe", "2024-10-10 to 2024-10-12");
        req1.setStatus(LeaveStatus.APPROVED);

        LeaveRequest req2 = new LeaveRequest("Jane Smith", "2024-11-01 to 2024-11-05");
        req1.setStatus(LeaveStatus.PENDING);
    }
}
