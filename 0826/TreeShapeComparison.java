public class TreeShapeComparison {

    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;
    private int size;

    public boolean add(int key) {
        if (root == null) {
            root = new Node(key);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            if (key == current.key) {
                return false;
            }

            if (key < current.key) {
                if (current.left == null) {
                    current.left = new Node(key);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(key);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
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

            if (target == current.key) {
                return count;
            }

            if (target < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return count;
    }

    public int totalSearchComparisons(int[] keys) {
        int total = 0;

        for (int key : keys) {
            total += searchComparisonCount(key);
        }

        return total;
    }

    public int size() {
        return size;
    }

    private static TreeShapeComparison buildTree(int[] order) {
        TreeShapeComparison tree = new TreeShapeComparison();

        for (int key : order) {
            tree.add(key);
        }

        return tree;
    }

    private static void printReport(
            String title,
            TreeShapeComparison tree,
            int[] allKeys,
            int missingKey) {

        System.out.println("=== " + title + " ===");
        System.out.println("size = " + tree.size());
        System.out.println("height = " + tree.height());

        System.out.println(
                "all key search comparison total = "
                        + tree.totalSearchComparisons(allKeys)
        );

        System.out.println(
                "missing key " + missingKey
                        + " comparison count = "
                        + tree.searchComparisonCount(missingKey)
        );

        System.out.println();
    }

    public static void main(String[] args) {

        int[] allKeys = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] ascendingOrder = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] descendingOrder = {
            150, 140, 130, 120, 110,
            100, 90, 80, 70, 60,
            50, 40, 30, 20, 10
        };

        int[] balancedOrder = {
            80,
            40, 120,
            20, 60, 100, 140,
            10, 30, 50, 70,
            90, 110, 130, 150
        };

        TreeShapeComparison ascendingTree =
                buildTree(ascendingOrder);

        TreeShapeComparison descendingTree =
                buildTree(descendingOrder);

        TreeShapeComparison balancedTree =
                buildTree(balancedOrder);

        int missingKey = 155;

        printReport(
                "Ascending Order",
                ascendingTree,
                allKeys,
                missingKey
        );

        printReport(
                "Descending Order",
                descendingTree,
                allKeys,
                missingKey
        );

        printReport(
                "Nearly Balanced Order",
                balancedTree,
                allKeys,
                missingKey
        );
    }
}