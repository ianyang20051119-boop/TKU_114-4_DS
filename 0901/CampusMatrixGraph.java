import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {

    private final boolean[][] matrix;
    private final int vertexCount;
    private int edgeCount;

    public CampusMatrixGraph(int vertexCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException();
        }

        this.vertexCount = vertexCount;
        this.matrix = new boolean[vertexCount][vertexCount];
        this.edgeCount = 0;
    }

    private boolean validVertex(int vertex) {
        return vertex >= 0 && vertex < vertexCount;
    }

    public boolean addEdge(int a, int b) {
        if (!validVertex(a) || !validVertex(b)) {
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

    public boolean removeEdge(int a, int b) {
        if (!validVertex(a) || !validVertex(b)) {
            return false;
        }

        if (!matrix[a][b]) {
            return false;
        }

        matrix[a][b] = false;
        matrix[b][a] = false;
        edgeCount--;

        return true;
    }

    public int degree(int vertex) {
        if (!validVertex(vertex)) {
            return -1;
        }

        int degree = 0;

        for (int i = 0; i < vertexCount; i++) {
            if (matrix[vertex][i]) {
                degree++;
            }
        }

        return degree;
    }

    public List<Integer> neighbors(int vertex) {
        List<Integer> result = new ArrayList<>();

        if (!validVertex(vertex)) {
            return result;
        }

        for (int i = 0; i < vertexCount; i++) {
            if (matrix[vertex][i]) {
                result.add(i);
            }
        }

        return result;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public static void main(String[] args) {
        CampusMatrixGraph graph = new CampusMatrixGraph(6);

        System.out.println("add 0-1: " + graph.addEdge(0, 1));
        System.out.println("add 0-2: " + graph.addEdge(0, 2));
        System.out.println("add 1-2: " + graph.addEdge(1, 2));
        System.out.println("add 1-3: " + graph.addEdge(1, 3));
        System.out.println("add 2-4: " + graph.addEdge(2, 4));
        System.out.println("add 4-5: " + graph.addEdge(4, 5));

        System.out.println(
                "duplicate 0-1: " + graph.addEdge(0, 1)
        );

        System.out.println();

        System.out.println("edge count: " + graph.getEdgeCount());
        System.out.println("degree 1: " + graph.degree(1));
        System.out.println("neighbors 1: " + graph.neighbors(1));
        System.out.println("neighbors 4: " + graph.neighbors(4));

        System.out.println();

        System.out.println(
                "remove 1-2: " + graph.removeEdge(1, 2)
        );

        System.out.println(
                "remove 1-2 again: " + graph.removeEdge(1, 2)
        );

        System.out.println();

        System.out.println("edge count: " + graph.getEdgeCount());
        System.out.println("degree 1: " + graph.degree(1));
        System.out.println("neighbors 1: " + graph.neighbors(1));
    }
}