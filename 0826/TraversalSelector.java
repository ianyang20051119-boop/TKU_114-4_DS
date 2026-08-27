public class TraversalSelector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }

        Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static String preorder(Node node) {
        if (node == null) {
            return "";
        }

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return node.value + " "
                + preorder(node.left) + " "
                + preorder(node.right);
    }

    public static String inorder(Node node) {
        if (node == null) {
            return "";
        }

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return "("
                + inorder(node.left)
                + " " + node.value + " "
                + inorder(node.right)
                + ")";
    }

    public static String postorder(Node node) {
        if (node == null) {
            return "";
        }

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return postorder(node.left) + " "
                + postorder(node.right) + " "
                + node.value;
    }

    public static void main(String[] args) {

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        Node multiply = new Node("*", b, c);
        Node add = new Node("+", a, multiply);

        Node subtract = new Node("-", d, e);

        Node root = new Node("/", add, subtract);

        System.out.println("Expression Tree");
        System.out.println();

        System.out.println(
                "Prefix  : " + preorder(root)
        );

        System.out.println(
                "Infix   : " + inorder(root)
        );

        System.out.println(
                "Postfix : " + postorder(root)
        );
    }
}