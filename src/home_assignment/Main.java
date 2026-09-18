package home_assignment;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        // Problem 1 Test
        System.out.println("--- Problem 1 ---");
        String[] batch = {"BIB1001", "B1", "BIB1002"};
        System.out.println(RaceEntry.registerBatch(batch, 80));

        // Problem 2 Test
        System.out.println("\n--- Problem 2 ---");
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry elite = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        runner.announce();
        elite.announce();
        relay.announce();

        System.out.println(RaceManager.classifyGeneration(elite));
        System.out.println(RaceManager.classifyGeneration(relay));

        RaceEntry[] field = {runner, elite, relay};
        System.out.println("Total Balance Due: " + RaceManager.getTotalBalanceDue(field));

        // Problem 3 Test (Late-Withdrawal Penalty & Audit Trail)
        System.out.println("\n--- Problem 3 ---");
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println("Balance after fee: " + r.getBalanceDue());

        // Defensive copy test
        double[] history = r.getLateFeeHistory();
        System.out.println("Recorded history: " + Arrays.toString(history));

        // Attempting to tamper with returned array
        history[0] = 999;
        System.out.println("Internal history after tampering attempt: " + Arrays.toString(r.getLateFeeHistory()));
    }
}