import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {

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

    public void dfs(String start) {
        if (start == null) {
            return;
        }

        start = start.trim();

        if (!graph.containsKey(start)) {
            return;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);
        printState("push " + start, stack, visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            printState("pop " + current, stack, visited);

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            List<String> neighbors =
                    new ArrayList<>(graph.get(current));

            neighbors.sort(String::compareTo);

            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String neighbor = neighbors.get(i);

                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    printState(
                            "push " + neighbor,
                            stack,
                            visited
                    );
                }
            }
        }

        System.out.println("DFS complete");
        System.out.println("visited = " + visited);
    }

    private void printState(
            String action,
            Deque<String> stack,
            Set<String> visited) {

        System.out.println(
                action
                + " | Stack=" + stack
                + " | visited=" + visited
        );
    }

    public static void main(String[] args) {

        IterativeDfsTrace graph =
                new IterativeDfsTrace();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");

        graph.dfs("A");
    }
}