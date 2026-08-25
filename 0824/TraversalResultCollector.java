import java.util.ArrayList;
import java.util.List;

public class TraversalResultCollector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    public static List<String> preorder(Node root) {
        List<String> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private static void preorder(Node node, List<String> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    public static List<String> inorder(Node root) {
        List<String> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private static void inorder(Node node, List<String> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    public static List<String> postorder(Node root) {
        List<String> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private static void postorder(Node node, List<String> result) {
        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    public static List<String> levelOrder(Node root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<Node> queue = new ArrayList<>();
        queue.add(root);

        int index = 0;

        while (index < queue.size()) {
            Node current = queue.get(index++);
            result.add(current.value);

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return result;
    }

    public static void showResults(String name, Node root) {
        System.out.println("=== " + name + " ===");
        System.out.println("Preorder: " + preorder(root));
        System.out.println("Inorder: " + inorder(root));
        System.out.println("Postorder: " + postorder(root));
        System.out.println("Level-order: " + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {

        Node emptyTree = null;
        showResults("Empty Tree", emptyTree);

        Node singleTree = new Node("A");
        showResults("Single Node", singleTree);

        Node leftSkewed = new Node("A");
        leftSkewed.left = new Node("B");
        leftSkewed.left.left = new Node("C");
        leftSkewed.left.left.left = new Node("D");
        showResults("Left-skewed Tree", leftSkewed);

        Node completeTree = new Node("A");
        completeTree.left = new Node("B");
        completeTree.right = new Node("C");
        completeTree.left.left = new Node("D");
        completeTree.left.right = new Node("E");
        completeTree.right.left = new Node("F");
        completeTree.right.right = new Node("G");
        showResults("Complete Tree", completeTree);
    }
}