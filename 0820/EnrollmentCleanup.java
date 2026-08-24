import java.util.*;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> students = new ArrayList<>(Arrays.asList(
            "Amy",
            "Bob",
            "Amy",
            "  ",
            null,
            "Charlie",
            "David",
            "Bob",
            "",
            "Eve"
        ));

        System.out.println("清理前：");
        System.out.println(students);

        Iterator<String> iterator = students.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();

            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        System.out.println("清理後：");
        System.out.println(students);

        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = new LinkedHashSet<>();

        for (String name : students) {
            if (!uniqueNames.add(name)) {
                duplicateNames.add(name);
            }
        }

        System.out.println("重複姓名：");
        System.out.println(duplicateNames);
    }
}