import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {

    private final Map<String, Set<String>> graph =
            new HashMap<>();

    public boolean addVertex(String vertex) {
        if (vertex == null || vertex.trim().isEmpty()) {
            return false;
        }

        vertex = vertex.trim();

        if (graph.containsKey(vertex)) {
            return false;
        }

        graph.put(vertex, new HashSet<>());
        return true;
    }

    public boolean addEdge(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        a = a.trim();
        b = b.trim();

        if (!graph.containsKey(a)
                || !graph.containsKey(b)
                || a.equals(b)) {
            return false;
        }

        if (graph.get(a).contains(b)) {
            return false;
        }

        graph.get(a).add(b);
        graph.get(b).add(a);

        return true;
    }

    public List<List<String>> components() {
        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        List<String> vertices =
                new ArrayList<>(graph.keySet());

        Collections.sort(vertices);

        for (String start : vertices) {
            if (visited.contains(start)) {
                continue;
            }

            List<String> component = new ArrayList<>();
            Queue<String> queue = new ArrayDeque<>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                component.add(current);

                for (String next : graph.get(current)) {
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            Collections.sort(component);
            result.add(component);
        }

        return result;
    }

    public int componentCount() {
        return components().size();
    }

    public List<String> largestComponent() {
        List<List<String>> all = components();
        List<String> largest = new ArrayList<>();

        for (List<String> component : all) {
            if (component.size() > largest.size()) {
                largest = new ArrayList<>(component);
            }
        }

        return largest;
    }

    public void printReport() {
        List<List<String>> all = components();

        for (int i = 0; i < all.size(); i++) {
            System.out.println(
                    "Component " + (i + 1)
                    + ": " + all.get(i)
            );
        }

        System.out.println(
                "Component count: " + all.size()
        );

        List<String> largest = new ArrayList<>();

        for (List<String> component : all) {
            if (component.size() > largest.size()) {
                largest = component;
            }
        }

        System.out.println(
                "Largest component: " + largest
        );

        System.out.println(
                "Largest size: " + largest.size()
        );
    }

    public static void main(String[] args) {

        NetworkComponents network =
                new NetworkComponents();

        network.addVertex("A");
        network.addVertex("B");
        network.addVertex("C");
        network.addVertex("D");
        network.addVertex("E");
        network.addVertex("F");
        network.addVertex("G");
        network.addVertex("H");

        network.addEdge("A", "B");
        network.addEdge("B", "C");
        network.addEdge("A", "C");

        network.addEdge("D", "E");

        network.addEdge("F", "G");
        network.addEdge("G", "H");
        network.addEdge("F", "H");

        network.printReport();
    }
}