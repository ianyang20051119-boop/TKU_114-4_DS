import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderByLine {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    public static void levelOrderByLine(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;

        while (!queue.isEmpty()) {
            int count = queue.size();

            System.out.print("Level " + level + " (count=" + count + "): ");

            for (int i = 0; i < count; i++) {
                Node current = queue.poll();

                System.out.print(current.value + " ");

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {

        Node root = new Node("M");

        root.left = new Node("F");
        root.right = new Node("T");

        root.left.left = new Node("B");

        root.right.left = new Node("R");
        root.right.right = new Node("Z");

        root.left.left.left = new Node("A");

        System.out.println("=== Tree ===");
        levelOrderByLine(root);

        System.out.println();
        System.out.println("=== Empty Tree ===");
        levelOrderByLine(null);
    }
}