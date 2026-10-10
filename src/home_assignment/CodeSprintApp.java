package home_assignment;

import java.util.*;

// Interface for scoring rule strategy
interface ScoringRule {
    double calculateScore(double idea, double execution, double presentation);
}

class InnovationScoringRule implements ScoringRule {
    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
    }
}

class OpenScoringRule implements ScoringRule {
    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Score {
    private double idea;
    private double execution;
    private double presentation;

    public Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    public double getIdea() { return idea; }
    public double getExecution() { return execution; }
    public double getPresentation() { return presentation; }
}

class Project {
    private String title;
    private Score score;

    public Project(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public Score getScore() { return score; }
    public void setScore(Score score) { this.score = score; }
}

class Team {
    private String name;
    private List<Student> members;
    private String trackName;
    private ScoringRule scoringRule;
    private Project project;

    public Team(String name, List<Student> members, String trackName, ScoringRule scoringRule) {
        this.name = name;
        this.members = members;
        this.trackName = trackName;
        this.scoringRule = scoringRule;
    }

    public String getName() { return name; }
    public List<Student> getMembers() { return members; }
    public String getTrackName() { return trackName; }
    public ScoringRule getScoringRule() { return scoringRule; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}

class Hackathon {
    public enum State { OPEN, JUDGING, PUBLISHED }

    private State state = State.OPEN;
    private List<Team> teams = new ArrayList<>();
    private Set<String> registeredStudentNames = new HashSet<>();

    public boolean registerTeam(String teamName, List<Student> members, String trackName, ScoringRule scoringRule) {
        if (members.size() < 2 || members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Student s : members) {
            if (registeredStudentNames.contains(s.getName())) {
                System.out.println("Registration failed: Student " + s.getName() + " is already in a team.");
                return false;
            }
        }

        Team team = new Team(teamName, members, trackName, scoringRule);
        teams.add(team);
        for (Student s : members) {
            registeredStudentNames.add(s.getName());
        }

        System.out.println("Team " + teamName + " registered (" + members.size() + " members, " + trackName + " track).");
        return true;
    }

    public void submitProject(Team team, String projectTitle) {
        if (state == State.PUBLISHED) {
            System.out.println("Submission failed: Results already published.");
            return;
        }
        Project p = new Project(projectTitle);
        team.setProject(p);
        System.out.println("Project '" + projectTitle + "' submitted by " + team.getName() + ".");
    }

    public void scoreProject(Project project, double idea, double execution, double presentation, Team team) {
        if (state == State.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }
        project.setScore(new Score(idea, execution, presentation));
        state = State.JUDGING;
        System.out.println("Score recorded for '" + project.getTitle() + "'.");

        double finalScore = team.getScoringRule().calculateScore(idea, execution, presentation);
        System.out.printf("Final score: %.2f.%n", finalScore);
    }

    public void publishResults() {
        this.state = State.PUBLISHED;
        System.out.println("Results published.");
    }
}

public class CodeSprintApp {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();

        // 1. Team 'ByteBusters' registers
        List<Student> byteBustersMembers = Arrays.asList(new Student("Asha"), new Student("Ravi"), new Student("Neha"));
        hackathon.registerTeam("ByteBusters", byteBustersMembers, "Innovation", new InnovationScoringRule());

        // 2. Team 'SoloCoder' registers (1 member -> Fails)
        List<Student> soloMembers = Arrays.asList(new Student("Kiran"));
        hackathon.registerTeam("SoloCoder", soloMembers, "Open", new OpenScoringRule());

        // 3. ByteBusters submits project 'SmartAttend'
        Team byteBusters = new Team("ByteBusters", byteBustersMembers, "Innovation", new InnovationScoringRule());
        Project smartAttend = new Project("SmartAttend");
        hackathon.submitProject(byteBusters, "SmartAttend");

        // 4. Judge scores 'SmartAttend'
        hackathon.scoreProject(smartAttend, 8, 7, 9, byteBusters);

        // 5. Organiser publishes results
        hackathon.publishResults();

        // 6. Judge attempts to rescore
        hackathon.scoreProject(smartAttend, 10, 7, 9, byteBusters);
    }
}