import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {

    private final String[] stations;
    private final boolean[][] matrix;
    private int edgeCount;

    public MetroMatrixGraph(String[] stations) {
        if (stations == null) {
            throw new IllegalArgumentException();
        }

        this.stations = stations.clone();
        this.matrix = new boolean[stations.length][stations.length];
        this.edgeCount = 0;
    }

    private int indexOf(String station) {
        if (station == null) {
            return -1;
        }

        for (int i = 0; i < stations.length; i++) {
            if (stations[i].equals(station)) {
                return i;
            }
        }

        return -1;
    }

    public boolean addEdge(String station1, String station2) {
        int a = indexOf(station1);
        int b = indexOf(station2);

        if (a == -1 || b == -1 || a == b) {
            return false;
        }

        if (matrix[a][b]) {
            return false;
        }

        matrix[a][b] = true;
        matrix[b][a] = true;
        edgeCount++;

        return true;
    }

    public List<String> neighbors(String station) {
        List<String> result = new ArrayList<>();

        int index = indexOf(station);

        if (index == -1) {
            return result;
        }

        for (int i = 0; i < stations.length; i++) {
            if (matrix[index][i]) {
                result.add(stations[i]);
            }
        }

        return result;
    }

    public int degree(String station) {
        int index = indexOf(station);

        if (index == -1) {
            return -1;
        }

        int count = 0;

        for (int i = 0; i < stations.length; i++) {
            if (matrix[index][i]) {
                count++;
            }
        }

        return count;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void matrixReport() {
        System.out.print("      ");

        for (String station : stations) {
            System.out.printf("%-6s", station);
        }

        System.out.println();

        for (int i = 0; i < stations.length; i++) {
            System.out.printf("%-6s", stations[i]);

            for (int j = 0; j < stations.length; j++) {
                System.out.printf(
                        "%-6d",
                        matrix[i][j] ? 1 : 0
                );
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        String[] stations = {
                "A", "B", "C", "D", "E", "F"
        };

        MetroMatrixGraph metro =
                new MetroMatrixGraph(stations);

        metro.addEdge("A", "B");
        metro.addEdge("A", "C");
        metro.addEdge("B", "C");
        metro.addEdge("B", "D");
        metro.addEdge("C", "E");
        metro.addEdge("D", "E");
        metro.addEdge("E", "F");

        System.out.println(
                "duplicate A-B: "
                + metro.addEdge("A", "B")
        );

        System.out.println();

        for (String station : stations) {
            System.out.println(
                    station
                    + " neighbors=" + metro.neighbors(station)
                    + " degree=" + metro.degree(station)
            );
        }

        System.out.println();

        System.out.println(
                "edge count: " + metro.getEdgeCount()
        );

        System.out.println();

        System.out.println("Matrix Report:");
        metro.matrixReport();
    }
}