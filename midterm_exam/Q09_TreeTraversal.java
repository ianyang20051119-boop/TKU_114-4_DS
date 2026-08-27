import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q09_TreeTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private static void preorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    public static List<Integer> inorder(Node root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private static void inorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> walkRecordP09 = new ArrayList<>();
        postorder(root, walkRecordP09);
        return walkRecordP09;
    }

    private static void postorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    public static List<Integer> levelOrder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            result.add(node.value);

            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }

        return result;
    }
}
