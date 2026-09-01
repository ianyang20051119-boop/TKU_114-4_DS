import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DirectedReachability {

    private final Map<String, List<String>> graph =
            new HashMap<>();

    public boolean addVertex(String vertex) {
        if (vertex == null || vertex.trim().isEmpty()) {
            return false;
        }

        vertex = vertex.trim();

        if (graph.containsKey(vertex)) {
            return false;
        }

        graph.put(vertex, new ArrayList<>());
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (graph.get(from).contains(to)) {
            return false;
        }

        graph.get(from).add(to);
        return true;
    }

    public boolean reachable(String from, String to) {
        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (from.equals(to)) {
            return true;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String next : graph.get(current)) {
                if (next.equals(to)) {
                    return true;
                }

                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        DirectedReachability graph =
                new DirectedReachability();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");

        String[][] queries = {
                {"A", "E"},
                {"A", "D"},
                {"B", "E"},
                {"C", "B"},
                {"E", "A"},
                {"A", "F"},
                {"A", "A"},
                {"X", "A"}
        };

        for (String[] query : queries) {
            String from = query[0];
            String to = query[1];

            System.out.println(
                    from + " -> " + to
                    + ": " + graph.reachable(from, to)
            );
        }
    }
}