import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {

    public static Set<String> union(
            Set<String> first, Set<String> second) {

        Set<String> result = new HashSet<>(first);
        result.addAll(second);

        return result;
    }

    public static Set<String> intersection(
            Set<String> first, Set<String> second) {

        Set<String> result = new HashSet<>(first);
        result.retainAll(second);

        return result;
    }

    public static Set<String> firstOnly(
            Set<String> first, Set<String> second) {

        Set<String> result = new HashSet<>(first);
        result.removeAll(second);

        return result;
    }

    public static Set<String> secondOnly(
            Set<String> first, Set<String> second) {

        Set<String> result = new HashSet<>(second);
        result.removeAll(first);

        return result;
    }

    public static void main(String[] args) {

        Set<String> first = new HashSet<>();
        first.add("Music");
        first.add("Travel");
        first.add("Gaming");
        first.add("Reading");

        Set<String> second = new HashSet<>();
        second.add("Travel");
        second.add("Sports");
        second.add("Reading");
        second.add("Cooking");

        System.out.println("First: " + first);
        System.out.println("Second: " + second);

        System.out.println(
                "Union: " + union(first, second)
        );

        System.out.println(
                "Intersection: "
                + intersection(first, second)
        );

        System.out.println(
                "First only: "
                + firstOnly(first, second)
        );

        System.out.println(
                "Second only: "
                + secondOnly(first, second)
        );

        System.out.println();
        System.out.println("After comparison:");
        System.out.println("First: " + first);
        System.out.println("Second: " + second);
    }
}