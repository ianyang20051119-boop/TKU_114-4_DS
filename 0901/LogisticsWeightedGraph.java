import java.util.HashMap;
import java.util.Map;

public class LogisticsWeightedGraph {

    private final Map<String, Map<String, Integer>> graph =
            new HashMap<>();

    public boolean addVertex(String vertex) {
        if (vertex == null || vertex.trim().isEmpty()) {
            return false;
        }

        vertex = vertex.trim();

        if (graph.containsKey(vertex)) {
            return false;
        }

        graph.put(vertex, new HashMap<>());
        return true;
    }

    public boolean addEdge(
            String from, String to, int weight) {

        if (from == null || to == null || weight < 0) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (graph.get(from).containsKey(to)) {
            return false;
        }

        graph.get(from).put(to, weight);
        return true;
    }

    public boolean updateEdge(
            String from, String to, int newWeight) {

        if (from == null || to == null || newWeight < 0) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (!graph.get(from).containsKey(to)) {
            return false;
        }

        graph.get(from).put(to, newWeight);
        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        return graph.get(from).remove(to) != null;
    }

    public Integer getWeight(String from, String to) {
        if (from == null || to == null) {
            return null;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return null;
        }

        return graph.get(from).get(to);
    }

    public void printGraph() {
        for (String from : graph.keySet()) {
            System.out.println(
                    from + " -> " + graph.get(from)
            );
        }
    }

    public static void main(String[] args) {

        LogisticsWeightedGraph graph =
                new LogisticsWeightedGraph();

        graph.addVertex("Warehouse");
        graph.addVertex("StoreA");
        graph.addVertex("StoreB");
        graph.addVertex("Hub");

        System.out.println(
                "add Warehouse -> Hub: "
                + graph.addEdge(
                        "Warehouse", "Hub", 15
                )
        );

        System.out.println(
                "add Hub -> StoreA: "
                + graph.addEdge(
                        "Hub", "StoreA", 8
                )
        );

        System.out.println(
                "add Hub -> StoreB: "
                + graph.addEdge(
                        "Hub", "StoreB", 12
                )
        );

        System.out.println(
                "add Warehouse -> StoreA: "
                + graph.addEdge(
                        "Warehouse", "StoreA", 25
                )
        );

        System.out.println();

        System.out.println(
                "negative weight: "
                + graph.addEdge(
                        "StoreA", "StoreB", -5
                )
        );

        System.out.println(
                "missing vertex: "
                + graph.addEdge(
                        "Warehouse", "Unknown", 10
                )
        );

        System.out.println();

        System.out.println(
                "Warehouse -> Hub weight: "
                + graph.getWeight(
                        "Warehouse", "Hub"
                )
        );

        System.out.println(
                "update Warehouse -> Hub: "
                + graph.updateEdge(
                        "Warehouse", "Hub", 10
                )
        );

        System.out.println(
                "new weight: "
                + graph.getWeight(
                        "Warehouse", "Hub"
                )
        );

        System.out.println();

        System.out.println(
                "remove Hub -> StoreB: "
                + graph.removeEdge(
                        "Hub", "StoreB"
                )
        );

        System.out.println(
                "remove again: "
                + graph.removeEdge(
                        "Hub", "StoreB"
                )
        );

        System.out.println();

        System.out.println("Graph:");
        graph.printGraph();
    }
}