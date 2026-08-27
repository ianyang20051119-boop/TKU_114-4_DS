import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> enrollmentMapR26 = new TreeMap<>();

    public boolean enroll(String courseCode, String studentId) {
        if (isBlank(courseCode) || isBlank(studentId)) {
            return false;
        }

        String course = courseCode.trim();
        String student = studentId.trim();

        Set<String> students = enrollmentMapR26.computeIfAbsent(course, k -> new TreeSet<>());
        if (!students.add(student)) {
            return false;
        }
        return true;
    }

    public boolean drop(String courseCode, String studentId) {
        if (isBlank(courseCode) || isBlank(studentId)) {
            return false;
        }

        String course = courseCode.trim();
        String student = studentId.trim();

        Set<String> students = enrollmentMapR26.get(course);
        if (students == null || !students.remove(student)) {
            return false;
        }

        if (students.isEmpty()) {
            enrollmentMapR26.remove(course);
        }
        return true;
    }

    public int courseSize(String courseCode) {
        if (isBlank(courseCode)) {
            return 0;
        }
        Set<String> students = enrollmentMapR26.get(courseCode.trim());
        return students == null ? 0 : students.size();
    }

    public List<String> studentsOf(String courseCode) {
        if (isBlank(courseCode)) {
            return new ArrayList<>();
        }
        Set<String> students = enrollmentMapR26.get(courseCode.trim());
        return students == null ? new ArrayList<>() : new ArrayList<>(students);
    }

    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();
        if (isBlank(studentId)) {
            return result;
        }

        String student = studentId.trim();
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            if (entry.getValue().contains(student)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
