public class BstShapeExperiment {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
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
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    public int searchComparisonCount(int target) {
        Node current = root;
        int count = 0;

        while (current != null) {
            count++;

            if (target == current.value) {
                return count;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return count;
    }

    public int totalSearchComparisonCount(int[] values) {
        int total = 0;

        for (int value : values) {
            total += searchComparisonCount(value);
        }

        return total;
    }

    private static BstShapeExperiment buildTree(int[] order) {
        BstShapeExperiment tree = new BstShapeExperiment();

        for (int value : order) {
            tree.add(value);
        }

        return tree;
    }

    private static void printReport(
            String title,
            int[] order,
            int[] searchValues) {

        BstShapeExperiment tree = buildTree(order);

        System.out.println("=== " + title + " ===");

        System.out.print("Insert order: ");
        for (int value : order) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Size: " + tree.size());
        System.out.println("Height: " + tree.height());

        int total = 0;

        System.out.println("Search comparison count:");

        for (int value : searchValues) {
            int count = tree.searchComparisonCount(value);
            total += count;

            System.out.println(
                    value + " -> " + count
            );
        }

        System.out.println(
                "Total search comparison count: " + total
        );

        System.out.println();
    }

    public static void main(String[] args) {

        int[] values = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] sortedOrder = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] balancedOrder = {
            80,
            40, 120,
            20, 60, 100, 140,
            10, 30, 50, 70,
            90, 110, 130, 150
        };

        int[] mixedOrder = {
            50, 120, 20, 90, 140,
            10, 70, 110, 30, 150,
            60, 100, 40, 130, 80
        };

        printReport(
                "Sorted Order",
                sortedOrder,
                values
        );

        printReport(
                "Balanced Order",
                balancedOrder,
                values
        );

        printReport(
                "Mixed Order",
                mixedOrder,
                values
        );
    }
}