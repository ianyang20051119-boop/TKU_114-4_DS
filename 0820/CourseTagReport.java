import java.util.*;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] input = {
            "Java", "Database", "Java", "Algorithm",
            "UI/UX", "Database", "Java", "UI/UX"
        };

        List<String> list = new ArrayList<>();
        Set<String> set = new LinkedHashSet<>();
        Map<String, Integer> map = new LinkedHashMap<>();

        for (String tag : input) {
            list.add(tag);
            set.add(tag);
            map.put(tag, map.getOrDefault(tag, 0) + 1);
        }

        System.out.println("List：");
        System.out.println(list);
        System.out.println("用途：保存所有標籤及原始順序。");

        System.out.println();

        System.out.println("Set：");
        System.out.println(set);
        System.out.println("用途：保存不重複的課程標籤。");

        System.out.println();

        System.out.println("Map：");
        System.out.println(map);
        System.out.println("用途：統計每個課程標籤出現的次數。");
    }
}