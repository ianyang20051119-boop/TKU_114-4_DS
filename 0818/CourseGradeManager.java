class CourseGrade {

    private String studentId;
    private String name;
    private double daily;
    private double midterm;
    private double finalExam;
    private double attendance;

    public CourseGrade(String studentId, String name,
                       double daily, double midterm,
                       double finalExam, double attendance) {

        this.studentId = studentId;
        this.name = name;
        this.daily = validScore(daily);
        this.midterm = validScore(midterm);
        this.finalExam = validScore(finalExam);
        this.attendance = validScore(attendance);
    }

    private double validScore(double score) {
        if (score < 0) {
            return 0;
        }

        if (score > 100) {
            return 100;
        }

        return score;
    }

    public double calculateFinalScore() {
        return daily * 0.5
                + midterm * 0.2
                + finalExam * 0.2
                + attendance * 0.1;
    }

    public String getLevel() {
        double score = calculateFinalScore();

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return "學號：" + studentId
                + "，姓名：" + name
                + "，平時：" + daily
                + "，期中：" + midterm
                + "，期末：" + finalExam
                + "，出席：" + attendance
                + "，總分：" + calculateFinalScore()
                + "，等級：" + getLevel();
    }
}

public class CourseGradeManager {

    public static void main(String[] args) {

        CourseGrade student1 =
                new CourseGrade("A001", "王小明", 90, 85, 88, 100);

        CourseGrade student2 =
                new CourseGrade("A002", "李小華", 75, 70, 68, 90);

        System.out.println(student1);
        System.out.println(student2);
    }
}