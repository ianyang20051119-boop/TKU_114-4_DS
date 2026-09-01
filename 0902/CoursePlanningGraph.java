import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {

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

    public boolean addPrerequisite(String prerequisite, String course) {
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

    public boolean reachable(String from, String to) {
        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        return dfsReachable(from, to, visited);
    }

    private boolean dfsReachable(
            String current,
            String target,
            Set<String> visited) {

        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        for (String next : graph.get(current)) {
            if (!visited.contains(next)) {
                if (dfsReachable(next, target, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<String> affectedCourses(String course) {
        List<String> result = new ArrayList<>();

        if (course == null) {
            return result;
        }

        course = course.trim();

        if (!graph.containsKey(course)) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        dfsAffected(course, visited);

        visited.remove(course);

        result.addAll(visited);
        Collections.sort(result);

        return result;
    }

    private void dfsAffected(
            String current,
            Set<String> visited) {

        if (!visited.add(current)) {
            return;
        }

        for (String next : graph.get(current)) {
            dfsAffected(next, visited);
        }
    }

    public void printGraph() {
        List<String> courses = new ArrayList<>(graph.keySet());
        Collections.sort(courses);

        for (String course : courses) {
            List<String> next =
                    new ArrayList<>(graph.get(course));

            Collections.sort(next);

            System.out.println(
                    course + " -> " + next
            );
        }
    }

    public static void main(String[] args) {

        CoursePlanningGraph planning =
                new CoursePlanningGraph();

        planning.addCourse("Programming");
        planning.addCourse("DataStructures");
        planning.addCourse("Algorithms");
        planning.addCourse("Database");
        planning.addCourse("AI");
        planning.addCourse("MachineLearning");
        planning.addCourse("Web");

        planning.addPrerequisite(
                "Programming", "DataStructures");

        planning.addPrerequisite(
                "DataStructures", "Algorithms");

        planning.addPrerequisite(
                "Algorithms", "AI");

        planning.addPrerequisite(
                "Algorithms", "MachineLearning");

        planning.addPrerequisite(
                "Programming", "Database");

        planning.addPrerequisite(
                "Programming", "Web");

        System.out.println("Course Graph:");
        planning.printGraph();

        System.out.println();

        System.out.println(
                "Programming -> AI: "
                + planning.reachable("Programming", "AI")
        );

        System.out.println(
                "Database -> AI: "
                + planning.reachable("Database", "AI")
        );

        System.out.println(
                "DataStructures -> MachineLearning: "
                + planning.reachable(
                        "DataStructures",
                        "MachineLearning")
        );

        System.out.println();

        System.out.println(
                "Affected by Programming: "
                + planning.affectedCourses("Programming")
        );

        System.out.println(
                "Affected by DataStructures: "
                + planning.affectedCourses("DataStructures")
        );

        System.out.println(
                "Affected by Algorithms: "
                + planning.affectedCourses("Algorithms")
        );
    }
}