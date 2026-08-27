import java.util.ArrayList;
import java.util.List;

public class BstRangeStatistics {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }

        Node current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public List<Integer> valuesBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();

        if (low > high) {
            return result;
        }

        valuesBetween(root, low, high, result);
        return result;
    }

    private void valuesBetween(
            Node node,
            int low,
            int high,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        if (node.value > low) {
            valuesBetween(node.left, low, high, result);
        }

        if (node.value >= low && node.value <= high) {
            result.add(node.value);
        }

        if (node.value < high) {
            valuesBetween(node.right, low, high, result);
        }
    }

    public int countBetween(int low, int high) {
        if (low > high) {
            return 0;
        }

        return countBetween(root, low, high);
    }

    private int countBetween(Node node, int low, int high) {
        if (node == null) {
            return 0;
        }

        if (node.value < low) {
            return countBetween(node.right, low, high);
        }

        if (node.value > high) {
            return countBetween(node.left, low, high);
        }

        return 1
                + countBetween(node.left, low, high)
                + countBetween(node.right, low, high);
    }

    public int sumBetween(int low, int high) {
        if (low > high) {
            return 0;
        }

        return sumBetween(root, low, high);
    }

    private int sumBetween(Node node, int low, int high) {
        if (node == null) {
            return 0;
        }

        if (node.value < low) {
            return sumBetween(node.right, low, high);
        }

        if (node.value > high) {
            return sumBetween(node.left, low, high);
        }

        return node.value
                + sumBetween(node.left, low, high)
                + sumBetween(node.right, low, high);
    }

    private void printStatistics(int low, int high) {
        System.out.println("Range [" + low + ", " + high + "]");
        System.out.println(
                "values = " + valuesBetween(low, high)
        );
        System.out.println(
                "count = " + countBetween(low, high)
        );
        System.out.println(
                "sum = " + sumBetween(low, high)
        );
        System.out.println();
    }

    public static void main(String[] args) {
        BstRangeStatistics tree = new BstRangeStatistics();

        int[] values = {
            50, 30, 70, 20, 40,
            60, 80, 10, 25, 65, 90
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("=== BST Range Statistics ===");
        System.out.println();

        tree.printStatistics(30, 70);

        tree.printStatistics(20, 60);

        tree.printStatistics(100, 200);

        tree.printStatistics(70, 30);
    }
}