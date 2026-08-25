public class MenuTreeSearch {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    public static boolean contains(Node root, String target) {
        if (root == null) {
            return false;
        }

        if (root.value.equals(target)) {
            return true;
        }

        return contains(root.left, target) || contains(root.right, target);
    }

    public static int findDepth(Node root, String target) {
        return findDepth(root, target, 0);
    }

    private static int findDepth(Node node, String target, int depth) {
        if (node == null) {
            return -1;
        }

        if (node.value.equals(target)) {
            return depth;
        }

        int leftDepth = findDepth(node.left, target, depth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        }

        return findDepth(node.right, target, depth + 1);
    }

    public static int countLeaves(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {

        Node root = new Node("Menu");

        root.left = new Node("Food");
        root.right = new Node("Drink");

        root.left.left = new Node("Burger");
        root.left.right = new Node("Pizza");

        root.right.left = new Node("Coffee");
        root.right.right = new Node("Tea");

        root.left.left.left = new Node("Beef Burger");

        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();

        System.out.println("contains Coffee: "
                + contains(root, "Coffee"));

        System.out.println("contains Juice: "
                + contains(root, "Juice"));

        System.out.println("Depth of Coffee: "
                + findDepth(root, "Coffee"));

        System.out.println("Depth of Juice: "
                + findDepth(root, "Juice"));

        System.out.println("Leaf count: "
                + countLeaves(root));
    }
}