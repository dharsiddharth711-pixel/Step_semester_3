package practice_assignment;

import java.util.*;

// Polymorphic Question Abstraction
abstract class Question {
    protected int id;
    protected String prompt;

    public Question(int id, String prompt) {
        this.id = id;
        this.prompt = prompt;
    }

    public int getId() { return id; }
    public abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(int id, String prompt, String correctAnswer) {
        super(id, prompt);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Examination(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() { return questions; }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean isSubmitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        System.out.println("Examination '" + examination.getTitle() + "' started by " + student.getName() + ".");
    }

    public void answerQuestion(int questionId, String answer) {
        if (isSubmitted) {
            System.out.println("Cannot change answers after submission.");
            return;
        }
        answers.put(questionId, answer);
        System.out.println("Question " + questionId + " answered with '" + answer + "'.");
    }

    public void submit() {
        if (isSubmitted) {
            System.out.println("Attempt already submitted.");
            return;
        }
        isSubmitted = true;
        System.out.println("Examination '" + examination.getTitle() + "' submitted successfully.");
        evaluate();
    }

    private void evaluate() {
        int score = 0;
        int total = examination.getQuestions().size();

        for (Question q : examination.getQuestions()) {
            String ans = answers.get(q.getId());
            if (ans != null && q.isCorrect(ans)) {
                score++;
            }
        }
        System.out.println("Result for '" + examination.getTitle() + "' attempt: " + score + "/" + total + " correct");
    }
}

public class ExamSystemApp {
    public static void main(String[] args) {
        Examination exam = new Examination("Math Quiz");
        exam.addQuestion(new MultipleChoiceQuestion(1, "Question 1 Prompt", "A"));
        exam.addQuestion(new MultipleChoiceQuestion(2, "Question 2 Prompt", "B"));

        Student student = new Student("Student");
        Attempt attempt = new Attempt(student, exam);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");
        attempt.submit();
    }
}