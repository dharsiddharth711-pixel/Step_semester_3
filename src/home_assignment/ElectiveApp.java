package home_assignment;

import java.util.*;

public class ElectiveApp {

    // --- Student Types ---
    abstract static class StudentType {
        public abstract int getCreditLimit();
        public abstract String getTypeName();
    }

    static class RegularStudent extends StudentType {
        @Override public int getCreditLimit() { return 24; }
        @Override public String getTypeName() { return "Regular"; }
    }

    static class HonorsStudent extends StudentType {
        @Override public int getCreditLimit() { return 28; }
        @Override public String getTypeName() { return "Honors"; }
    }

    static class ExchangeStudent extends StudentType {
        @Override public int getCreditLimit() { return 20; }
        @Override public String getTypeName() { return "Exchange"; }
    }

    // --- Student Class ---
    static class Student {
        private String name;
        private StudentType type;
        private int currentCredits;

        public Student(String name, StudentType type, int currentCredits) {
            this.name = name;
            this.type = type;
            this.currentCredits = currentCredits;
        }

        public String getName() { return name; }
        public StudentType getType() { return type; }
        public int getCurrentCredits() { return currentCredits; }
        public void addCredits(int credits) { this.currentCredits += credits; }
        public void removeCredits(int credits) { this.currentCredits -= credits; }
    }

    // --- Elective Class ---
    static class Elective {
        private String name;
        private int credits;
        private int capacity;
        private List<Student> enrolled = new ArrayList<>();
        private Queue<Student> waitlist = new LinkedList<>();

        public Elective(String name, int credits, int capacity) {
            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
        }

        public void enrollStudent(Student s) {
            int projectedCredits = s.getCurrentCredits() + credits;
            if (projectedCredits > s.getType().getCreditLimit()) {
                System.out.printf("Enrollment failed: %s would exceed the %s credit limit (%d/%d).%n",
                        s.getName(), s.getType().getTypeName(), projectedCredits, s.getType().getCreditLimit());
                return;
            }

            if (enrolled.contains(s) || waitlist.contains(s)) {
                System.out.println("Enrollment failed: Student is already enrolled or waitlisted.");
                return;
            }

            if (enrolled.size() < capacity) {
                enrolled.add(s);
                s.addCredits(credits);
                System.out.printf("%s enrolled in %s (credits: %d/%d).%n",
                        s.getName(), name, s.getCurrentCredits(), s.getType().getCreditLimit());
            } else {
                waitlist.add(s);
                System.out.printf("%s added to waitlist (position %d).%n", s.getName(), waitlist.size());
            }
        }

        public void dropStudent(Student s) {
            if (!enrolled.contains(s)) return;

            enrolled.remove(s);
            s.removeCredits(credits);
            System.out.printf("%s dropped %s (credits: %d/%d).%n",
                    s.getName(), name, s.getCurrentCredits(), s.getType().getCreditLimit());

            while (!waitlist.isEmpty()) {
                Student candidate = waitlist.poll();
                if (candidate.getCurrentCredits() + credits <= candidate.getType().getCreditLimit()) {
                    enrolled.add(candidate);
                    candidate.addCredits(credits);
                    System.out.printf("%s promoted from waitlist and enrolled in %s (credits: %d/%d).%n",
                            candidate.getName(), name, candidate.getCurrentCredits(), candidate.getType().getCreditLimit());
                    break;
                }
            }
        }
    }

    // --- Main Method ---
    public static void main(String[] args) {
        Elective cloud = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", new RegularStudent(), 20);
        Student ravi = new Student("Ravi", new HonorsStudent(), 22);
        Student neha = new Student("Neha", new ExchangeStudent(), 12);
        Student kiran = new Student("Kiran", new RegularStudent(), 22);

        cloud.enrollStudent(asha);
        cloud.enrollStudent(ravi);
        System.out.println("Cloud Computing is full.");
        cloud.enrollStudent(neha);
        cloud.enrollStudent(kiran);

        cloud.dropStudent(asha);
    }
}