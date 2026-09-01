import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {

    private final Map<String, Set<String>> graph = new HashMap<>();

    public boolean addCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            return false;
        }

        course = course.trim();

        if (graph.containsKey(course)) {
            return false;
        }

        graph.put(course, new HashSet<>());
        return true;
    }

    public boolean addDependency(
            String prerequisite, String course) {

        if (prerequisite == null || course == null) {
            return false;
        }

        prerequisite = prerequisite.trim();
        course = course.trim();

        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(course)
                || prerequisite.equals(course)) {
            return false;
        }

        return graph.get(prerequisite).add(course);
    }

    public List<String> prerequisites(String course) {
        List<String> result = new ArrayList<>();

        if (course == null || !graph.containsKey(course.trim())) {
            return result;
        }

        course = course.trim();

        for (Map.Entry<String, Set<String>> entry
                : graph.entrySet()) {

            if (entry.getValue().contains(course)) {
                result.add(entry.getKey());
            }
        }

        result.sort(String::compareTo);
        return result;
    }

    public List<String> nextCourses(String course) {
        List<String> result = new ArrayList<>();

        if (course == null || !graph.containsKey(course.trim())) {
            return result;
        }

        course = course.trim();

        result.addAll(graph.get(course));
        result.sort(String::compareTo);

        return result;
    }

    public int inDegree(String course) {
        if (course == null || !graph.containsKey(course.trim())) {
            return -1;
        }

        return prerequisites(course).size();
    }

    public int outDegree(String course) {
        if (course == null || !graph.containsKey(course.trim())) {
            return -1;
        }

        return graph.get(course.trim()).size();
    }

    public void printReport() {
        List<String> courses = new ArrayList<>(graph.keySet());
        courses.sort(String::compareTo);

        for (String course : courses) {
            System.out.println(
                    course
                    + " prerequisites=" + prerequisites(course)
                    + " next=" + nextCourses(course)
                    + " in=" + inDegree(course)
                    + " out=" + outDegree(course)
            );
        }
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph =
                new CourseDependencyGraph();

        graph.addCourse("CS101");
        graph.addCourse("CS102");
        graph.addCourse("CS201");
        graph.addCourse("CS202");
        graph.addCourse("CS301");
        graph.addCourse("CS302");

        graph.addDependency("CS101", "CS201");
        graph.addDependency("CS102", "CS201");
        graph.addDependency("CS101", "CS202");

        graph.addDependency("CS201", "CS301");
        graph.addDependency("CS202", "CS301");

        graph.addDependency("CS201", "CS302");

        System.out.println("Course Dependency Report:");
        graph.printReport();
    }
}