import java.util.*;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizApplication {
    private List<Question> questions;
    private int score;
    private Map<Integer, Boolean> answerSummary;

    public QuizApplication() {
        questions = new ArrayList<>();
        score = 0;
        answerSummary = new HashMap<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int questionNumber = 1;

        for (Question question : questions) {
            System.out.println("Question " + questionNumber + ": " + question.getQuestionText());
            String[] options = question.getOptions();

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            System.out.print("You have 10 seconds to answer. Your choice: ");
            long startTime = System.currentTimeMillis();
            int userAnswer = -1;

            while ((System.currentTimeMillis() - startTime) < 10000 && scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            if ((System.currentTimeMillis() - startTime) >= 10000) {
                System.out.println("Time's up!");
                answerSummary.put(questionNumber, false);
            } else if (userAnswer == (question.getCorrectAnswerIndex() + 1)) {
                System.out.println("Correct!");
                score++;
                answerSummary.put(questionNumber, true);
            } else {
                System.out.println("Incorrect. The correct answer was: " + (question.getCorrectAnswerIndex() + 1));
                answerSummary.put(questionNumber, false);
            }

            questionNumber++;
            System.out.println();
        }

        displayResults();
        scanner.close();
    }

    private void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.size());
        System.out.println("Answer Summary:");

        for (int questionNumber : answerSummary.keySet()) {
            System.out.println("Question " + questionNumber + ": " + (answerSummary.get(questionNumber) ? "Correct" : "Incorrect"));
        }
    }

    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();

        quiz.addQuestion(new Question(
            "What is the capital of India?",
            new String[] {"New Delhi", "Mumbai", "Bengaluru", "Kolkata"},
            0
        ));

        quiz.addQuestion(new Question(
            "Which planet is known as the Red Planet?",
            new String[] {"Earth", "Mars", "Jupiter", "Saturn"},
            1
        ));

        quiz.addQuestion(new Question(
            "Who wrote 'Ramayana Darshanam'?",
            new String[] {"Chandrashekhar Kambar", "Shivaram Karanth", "Kuvempu", "Girish Karnad"},
            2
        ));

        quiz.startQuiz();
    }
}
