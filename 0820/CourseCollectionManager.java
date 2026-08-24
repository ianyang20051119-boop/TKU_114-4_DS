import java.util.*;

class CourseStudent {
    private String studentId;
    private String name;
    private String tag;
    private int score;

    public CourseStudent(String studentId, String name, String tag, int score) {
        this.studentId = studentId;
        this.name = name;
        this.tag = tag;
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return studentId + " " + name + " " + tag + " " + score;
    }
}

public class CourseCollectionManager {
    private List<CourseStudent> students;
    private Set<String> studentIds;
    private Map<String, CourseStudent> studentMap;

    public CourseCollectionManager() {
        students = new ArrayList<>();
        studentIds = new LinkedHashSet<>();
        studentMap = new LinkedHashMap<>();
    }

    public boolean add(CourseStudent student) {
        if (student == null || student.getStudentId() == null ||
            student.getStudentId().trim().isEmpty()) {
            return false;
        }

        if (!studentIds.add(student.getStudentId())) {
            return false;
        }

        students.add(student);
        studentMap.put(student.getStudentId(), student);

        return true;
    }

    public boolean updateScore(String studentId, int score) {
        CourseStudent student = studentMap.get(studentId);

        if (student == null) {
            return false;
        }

        student.setScore(score);
        return true;
    }

    public List<CourseStudent> findByTag(String tag) {
        List<CourseStudent> result = new ArrayList<>();

        if (tag == null || tag.trim().isEmpty()) {
            return result;
        }

        for (CourseStudent student : students) {
            if (student.getTag() != null &&
                student.getTag().equalsIgnoreCase(tag.trim())) {
                result.add(student);
            }
        }

        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> result = new LinkedHashMap<>();

        result.put("A", 0);
        result.put("B", 0);
        result.put("C", 0);
        result.put("D", 0);
        result.put("F", 0);

        for (CourseStudent student : students) {
            String grade;

            if (student.getScore() >= 90) {
                grade = "A";
            } else if (student.getScore() >= 80) {
                grade = "B";
            } else if (student.getScore() >= 70) {
                grade = "C";
            } else if (student.getScore() >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            result.put(grade, result.get(grade) + 1);
        }

        return result;
    }

    public List<CourseStudent> top(int count) {
        List<CourseStudent> result = new ArrayList<>(students);

        result.sort(
            Comparator.comparingInt(CourseStudent::getScore)
                      .reversed()
                      .thenComparing(CourseStudent::getStudentId)
        );

        if (count <= 0) {
            return new ArrayList<>();
        }

        if (count > result.size()) {
            count = result.size();
        }

        return new ArrayList<>(result.subList(0, count));
    }

    public int removeBelow(int minimum) {
        Iterator<CourseStudent> iterator = students.iterator();
        int removed = 0;

        while (iterator.hasNext()) {
            CourseStudent student = iterator.next();

            if (student.getScore() < minimum) {
                iterator.remove();
                studentIds.remove(student.getStudentId());
                studentMap.remove(student.getStudentId());
                removed++;
            }
        }

        return removed;
    }

    public void printAll() {
        for (CourseStudent student : students) {
            System.out.println(student);
        }
    }

    public int size() {
        return students.size();
    }

    public static void main(String[] args) {
        CourseCollectionManager manager = new CourseCollectionManager();

        manager.add(new CourseStudent("S001", "Amy", "Java", 95));
        manager.add(new CourseStudent("S002", "Bob", "Database", 85));
        manager.add(new CourseStudent("S003", "Cindy", "Java", 85));
        manager.add(new CourseStudent("S004", "David", "", 72));
        manager.add(new CourseStudent("S005", "Eric", "UI/UX", 58));
        manager.add(new CourseStudent("S006", "Fiona", "Java", 72));
        manager.add(new CourseStudent("S002", "George", "Algorithm", 90));

        System.out.println("原始資料：");
        manager.printAll();

        System.out.println();

        System.out.println("更新 S006 成績：");
        System.out.println(manager.updateScore("S006", 88));

        System.out.println();

        System.out.println("Java 標籤：");
        System.out.println(manager.findByTag("Java"));

        System.out.println();

        System.out.println("成績分布：");
        System.out.println(manager.scoreDistribution());

        System.out.println();

        System.out.println("前 3 名：");
        for (CourseStudent student : manager.top(3)) {
            System.out.println(student);
        }

        System.out.println();

        System.out.println("移除 60 分以下：");
        System.out.println("移除筆數：" + manager.removeBelow(60));

        System.out.println();

        System.out.println("移除後 List：");
        manager.printAll();

        System.out.println();

        System.out.println("移除後 Map：");
        System.out.println(manager.studentMap);

        System.out.println();

        System.out.println("移除後 Set：");
        System.out.println(manager.studentIds);

        System.out.println();

        System.out.println("移除後成績分布：");
        System.out.println(manager.scoreDistribution());
    }
}