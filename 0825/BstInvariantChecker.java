public class BstInvariantChecker {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static boolean isValid(Node root) {
        return isValid(root, null, null);
    }

    private static boolean isValid(
            Node node,
            Integer min,
            Integer max) {

        if (node == null) {
            return true;
        }

        if (min != null && node.value <= min) {
            return false;
        }

        if (max != null && node.value >= max) {
            return false;
        }

        return isValid(node.left, min, node.value)
                && isValid(node.right, node.value, max);
    }

    private static Node createValidTree() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);

        return root;
    }

    private static Node createInvalidTree1() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(60);

        // 60 在 50 的左 subtree，
        // 雖然 60 > 30，但違反必須 < 50

        return root;
    }

    private static Node createInvalidTree2() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.right.left = new Node(40);
        root.right.right = new Node(80);

        // 40 在 50 的右 subtree，
        // 雖然 40 < 70，但違反必須 > 50

        return root;
    }

    private static Node createInvalidTree3() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.right = new Node(40);
        root.left.right.right = new Node(55);

        // 55 位於 50 的左 subtree 深層位置，
        // 所以仍然必須 < 50

        return root;
    }

    public static void main(String[] args) {

        Node validTree = createValidTree();

        Node invalidTree1 = createInvalidTree1();
        Node invalidTree2 = createInvalidTree2();
        Node invalidTree3 = createInvalidTree3();

        System.out.println("=== BST Invariant Checker ===");

        System.out.println(
                "Valid tree: "
                        + isValid(validTree)
        );

        System.out.println(
                "Invalid tree 1: "
                        + isValid(invalidTree1)
        );

        System.out.println(
                "Invalid tree 2: "
                        + isValid(invalidTree2)
        );

        System.out.println(
                "Invalid tree 3: "
                        + isValid(invalidTree3)
        );
    }
}