import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MetroTransferPath {

    private final Map<String, List<String>> graph =
            new HashMap<>();

    public boolean addStation(String station) {
        if (station == null || station.trim().isEmpty()) {
            return false;
        }

        station = station.trim();

        if (graph.containsKey(station)) {
            return false;
        }

        graph.put(station, new ArrayList<>());
        return true;
    }

    public boolean addConnection(String a, String b) {
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

    public List<String> shortestPath(
            String start, String end) {

        List<String> empty = new ArrayList<>();

        if (start == null || end == null) {
            return empty;
        }

        start = start.trim();
        end = end.trim();

        if (!graph.containsKey(start)
                || !graph.containsKey(end)) {
            return empty;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.offer(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            for (String next : graph.get(current)) {
                if (visited.add(next)) {
                    parent.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.contains(end)) {
            return empty;
        }

        List<String> path = new ArrayList<>();

        String current = end;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    public void printPath(String start, String end) {
        List<String> path = shortestPath(start, end);

        System.out.println(
                start + " -> " + end
        );

        if (path.isEmpty()) {
            System.out.println("No path");
            System.out.println("edge count: -1");
            return;
        }

        System.out.println(
                "path: " + String.join(" -> ", path)
        );

        System.out.println(
                "station count: " + path.size()
        );

        System.out.println(
                "edge count: " + (path.size() - 1)
        );
    }

    public static void main(String[] args) {

        MetroTransferPath metro =
                new MetroTransferPath();

        metro.addStation("A");
        metro.addStation("B");
        metro.addStation("C");
        metro.addStation("D");
        metro.addStation("E");
        metro.addStation("F");
        metro.addStation("G");

        metro.addConnection("A", "B");
        metro.addConnection("A", "C");
        metro.addConnection("B", "D");
        metro.addConnection("C", "D");
        metro.addConnection("D", "E");
        metro.addConnection("E", "F");

        metro.printPath("A", "F");

        System.out.println();

        metro.printPath("A", "D");

        System.out.println();

        metro.printPath("A", "G");
    }
}