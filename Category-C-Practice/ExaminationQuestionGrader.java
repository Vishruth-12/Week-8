import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExaminationQuestionGrader {
    abstract static class Question {
        final String questionText, correctAnswer, studentAnswer;
        final double points;
        Question(String questionText, String correctAnswer, String studentAnswer, double points) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }
        abstract double grade();
        abstract String type();
    }

    static class MCQ extends Question {
        MCQ(String q, String c, String a, double p) { super(q, c, a, p); }
        double grade() { return studentAnswer.equals(correctAnswer) ? points : 0; }
        String type() { return "MCQ"; }
    }

    static class TrueFalse extends Question {
        TrueFalse(String q, String c, String a, double p) { super(q, c, a, p); }
        double grade() { return studentAnswer.equals(correctAnswer) ? points : 0; }
        String type() { return "TF"; }
    }

    static class Essay extends Question {
        Essay(String q, String c, String a, double p) { super(q, c, a, p); }
        double grade() {
            String answer = studentAnswer.toLowerCase();
            int matches = 0;
            for (String keyword : correctAnswer.split(",")) {
                String word = keyword.trim().toLowerCase();
                if (!word.isEmpty() && answer.contains(word)) matches++;
            }
            if (matches >= 2) return points * 0.75;
            if (matches == 1) return points * 0.50;
            return 0;
        }
        String type() { return "ESSAY"; }
    }

    static String[] parseQuotedFields(String line) {
        List<String> values = new ArrayList<>();
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);
        while (m.find()) values.add(m.group(1) != null ? m.group(1) : m.group(2));
        return values.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = parseQuotedFields(sc.nextLine());
            String type = p[0];
            Question q;
            if (type.equals("MCQ")) q = new MCQ(p[1], p[2], p[3], Double.parseDouble(p[4]));
            else if (type.equals("TF")) q = new TrueFalse(p[1], p[2], p[3], Double.parseDouble(p[4]));
            else if (type.equals("ESSAY")) q = new Essay(p[1], p[2], p[3], Double.parseDouble(p[4]));
            else throw new IllegalArgumentException("Unknown question type: " + type);
            questions.add(q);
        }
        double total = 0;
        for (Question question : questions) {
            double score = question.grade();
            System.out.printf("%s: %.2f%n", question.type(), score);
            total += score;
        }
        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
