public class SkewedBstReport {

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

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
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

    public boolean contains(int target) {
        Node current = root;

        while (current != null) {
            if (target == current.value) {
                return true;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    private static void printReport(
            String title,
            SkewedBstReport tree,
            int target) {

        System.out.println("=== " + title + " ===");
        System.out.println("size = " + tree.size());
        System.out.println("height = " + tree.height());
        System.out.println("search target = " + target);
        System.out.println("found = " + tree.contains(target));
        System.out.println(
                "comparison count = "
                        + tree.searchComparisonCount(target)
        );
        System.out.println();
    }

    public static void main(String[] args) {

        SkewedBstReport sortedTree = new SkewedBstReport();

        int[] sortedData = {
            10, 20, 30, 40, 50, 60, 70
        };

        for (int value : sortedData) {
            sortedTree.add(value);
        }

        SkewedBstReport balancedTree = new SkewedBstReport();

        int[] balancedOrder = {
            40, 20, 60, 10, 30, 50, 70
        };

        for (int value : balancedOrder) {
            balancedTree.add(value);
        }

        int target = 70;

        printReport(
            "Sorted Data Tree",
            sortedTree,
            target
        );

        printReport(
            "Balanced Order Tree",
            balancedTree,
            target
        );
    }
}