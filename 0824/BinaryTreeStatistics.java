public class BinaryTreeStatistics {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static int size(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + size(root.left) + size(root.right);
    }

    public static int sum(Node root) {
        if (root == null) {
            return 0;
        }

        return root.value + sum(root.left) + sum(root.right);
    }

    public static int maximum(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty");
        }

        return maximumValue(root);
    }

    private static int maximumValue(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        return Math.max(
                node.value,
                Math.max(
                        maximumValue(node.left),
                        maximumValue(node.right)
                )
        );
    }

    public static int leafCount(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return leafCount(root.left) + leafCount(root.right);
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static boolean contains(Node root, int target) {
        if (root == null) {
            return false;
        }

        if (root.value == target) {
            return true;
        }

        return contains(root.left, target)
                || contains(root.right, target);
    }

    public static void main(String[] args) {

        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);

        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Maximum: " + maximum(root));
        System.out.println("Leaf count: " + leafCount(root));
        System.out.println("Height: " + height(root));
        System.out.println("Contains 60: " + contains(root, 60));
        System.out.println("Contains 100: " + contains(root, 100));

        Node emptyTree = null;

        System.out.println();
        System.out.println("Empty tree:");
        System.out.println("Size: " + size(emptyTree));
        System.out.println("Sum: " + sum(emptyTree));
        System.out.println("Leaf count: " + leafCount(emptyTree));
        System.out.println("Height: " + height(emptyTree));
        System.out.println("Contains 10: " + contains(emptyTree, 10));

        try {
            System.out.println("Maximum: " + maximum(emptyTree));
        } catch (IllegalArgumentException e) {
            System.out.println("Maximum: Empty tree");
        }
    }
}