import java.util.*;

class Question {
    String question;
    String[] options;
    int answer;

    Question(String q, String[] opt, int ans) {
        question = q;
        options = opt;
        answer = ans;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
            "Which language is used for Android development?",
            new String[]{"1) Java", "2) Python", "3) HTML", "4) C"},
            1
        ));

        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"1) extends", "2) implements", "3) inherit", "4) super"},
            1
        ));

        questions.add(new Question(
            "Which data structure uses FIFO?",
            new String[]{"1) Stack", "2) Queue", "3) Tree", "4) Graph"},
            2
        ));

        int score = 0;
        int qn = 1;

        for (Question q : questions) {
            System.out.println("Q" + qn + ": " + q.question);
            for (String opt : q.options) System.out.println(opt);
            System.out.print("Your answer: ");
            int userAns = sc.nextInt();
            if (userAns == q.answer) score++;
            System.out.println();
            qn++;
        }

        System.out.println("Quiz Completed!");
        System.out.println("Your Score: " + score + " out of " + questions.size());
        if (score == questions.size()) System.out.println("Excellent!");
        else if (score >= 2) System.out.println("Good Job!");
        else System.out.println("Better luck next time!");
    }
}
