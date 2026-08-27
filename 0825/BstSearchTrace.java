public class BstSearchTrace {

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

    public boolean searchWithTrace(int target) {
        Node current = root;
        int comparisonCount = 0;

        System.out.println("Search target: " + target);

        while (current != null) {
            comparisonCount++;

            System.out.println(
                "current value = " + current.value
                + ", comparison count = " + comparisonCount
            );

            if (target == current.value) {
                System.out.println("direction = FOUND");
                System.out.println("result = true");
                System.out.println("total comparisons = " + comparisonCount);
                System.out.println();
                return true;
            }

            if (target < current.value) {
                System.out.println("direction = LEFT");
                current = current.left;
            } else {
                System.out.println("direction = RIGHT");
                current = current.right;
            }
        }

        System.out.println("current value = null");
        System.out.println("direction = NOT FOUND");
        System.out.println("result = false");
        System.out.println("total comparisons = " + comparisonCount);
        System.out.println();

        return false;
    }

    public static void main(String[] args) {
        BstSearchTrace tree = new BstSearchTrace();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("=== Find Root ===");
        tree.searchWithTrace(50);

        System.out.println("=== Find Leaf ===");
        tree.searchWithTrace(20);

        System.out.println("=== Find Internal Node ===");
        tree.searchWithTrace(70);

        System.out.println("=== Find Missing Value ===");
        tree.searchWithTrace(65);
    }
}