import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class CourseGradeMap {

    private final Map<String, List<Integer>> gradeMap =
            new HashMap<>();

    public boolean addGrade(String courseCode, int grade) {
        if (courseCode == null
                || courseCode.trim().isEmpty()
                || grade < 0
                || grade > 100) {
            return false;
        }

        courseCode = courseCode.trim();

        gradeMap
                .computeIfAbsent(
                        courseCode,
                        k -> new ArrayList<>()
                )
                .add(grade);

        return true;
    }

    public double average(String courseCode) {
        if (courseCode == null) {
            return 0.0;
        }

        List<Integer> grades =
                gradeMap.get(courseCode.trim());

        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (int grade : grades) {
            total += grade;
        }

        return (double) total / grades.size();
    }

    public Integer highest(String courseCode) {
        if (courseCode == null) {
            return null;
        }

        List<Integer> grades =
                gradeMap.get(courseCode.trim());

        if (grades == null || grades.isEmpty()) {
            return null;
        }

        int max = grades.get(0);

        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }

        return max;
    }

    public void printSortedReport() {
        Map<String, List<Integer>> sorted =
                new TreeMap<>(gradeMap);

        for (Map.Entry<String, List<Integer>> entry
                : sorted.entrySet()) {

            String courseCode = entry.getKey();

            System.out.printf(
                    "%s grades=%s average=%.2f highest=%d%n",
                    courseCode,
                    entry.getValue(),
                    average(courseCode),
                    highest(courseCode)
            );
        }
    }

    public static void main(String[] args) {
        CourseGradeMap manager =
                new CourseGradeMap();

        manager.addGrade("CS102", 85);
        manager.addGrade("CS101", 90);
        manager.addGrade("CS103", 78);
        manager.addGrade("CS101", 80);
        manager.addGrade("CS102", 95);
        manager.addGrade("CS103", 88);
        manager.addGrade("CS101", 100);
        manager.addGrade("CS102", 75);

        System.out.printf(
                "CS101 average: %.2f%n",
                manager.average("CS101")
        );

        System.out.println(
                "CS101 highest: "
                + manager.highest("CS101")
        );

        System.out.println();

        System.out.println("Sorted Report:");
        manager.printSortedReport();
    }
}