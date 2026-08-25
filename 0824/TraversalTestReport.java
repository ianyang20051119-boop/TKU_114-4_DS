import java.util.ArrayList;
import java.util.List;

public class TraversalTestReport {

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

    public static void test(
            String name,
            Node root,
            List<String> expectedPreorder,
            List<String> expectedInorder,
            List<String> expectedPostorder,
            List<String> expectedLevelOrder) {

        List<String> actualPreorder = preorder(root);
        List<String> actualInorder = inorder(root);
        List<String> actualPostorder = postorder(root);
        List<String> actualLevelOrder = levelOrder(root);

        System.out.println("=== " + name + " ===");

        System.out.println("Preorder");
        System.out.println("預期：" + expectedPreorder);
        System.out.println("實際：" + actualPreorder);
        System.out.println("相同：" + expectedPreorder.equals(actualPreorder));

        System.out.println("Inorder");
        System.out.println("預期：" + expectedInorder);
        System.out.println("實際：" + actualInorder);
        System.out.println("相同：" + expectedInorder.equals(actualInorder));

        System.out.println("Postorder");
        System.out.println("預期：" + expectedPostorder);
        System.out.println("實際：" + actualPostorder);
        System.out.println("相同：" + expectedPostorder.equals(actualPostorder));

        System.out.println("Level-order");
        System.out.println("預期：" + expectedLevelOrder);
        System.out.println("實際：" + actualLevelOrder);
        System.out.println("相同：" + expectedLevelOrder.equals(actualLevelOrder));

        System.out.println();
    }

    public static void main(String[] args) {

        Node empty = null;

        test(
                "Empty Tree",
                empty,
                List.of(),
                List.of(),
                List.of(),
                List.of()
        );

        Node single = new Node("A");

        test(
                "Single Node",
                single,
                List.of("A"),
                List.of("A"),
                List.of("A"),
                List.of("A")
        );

        Node onlyLeft = new Node("A");
        onlyLeft.left = new Node("B");
        onlyLeft.left.left = new Node("C");

        test(
                "Only Left",
                onlyLeft,
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C")
        );

        Node onlyRight = new Node("A");
        onlyRight.right = new Node("B");
        onlyRight.right.right = new Node("C");

        test(
                "Only Right",
                onlyRight,
                List.of("A", "B", "C"),
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C")
        );

        Node complete = new Node("A");
        complete.left = new Node("B");
        complete.right = new Node("C");
        complete.left.left = new Node("D");
        complete.left.right = new Node("E");
        complete.right.left = new Node("F");
        complete.right.right = new Node("G");

        test(
                "Complete Tree",
                complete,
                List.of("A", "B", "D", "E", "C", "F", "G"),
                List.of("D", "B", "E", "A", "F", "C", "G"),
                List.of("D", "E", "B", "F", "G", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F", "G")
        );

        Node irregular = new Node("A");
        irregular.left = new Node("B");
        irregular.right = new Node("C");
        irregular.left.right = new Node("D");
        irregular.right.left = new Node("E");
        irregular.right.left.right = new Node("F");

        test(
                "Irregular Tree",
                irregular,
                List.of("A", "B", "D", "C", "E", "F"),
                List.of("B", "D", "A", "E", "F", "C"),
                List.of("D", "B", "F", "E", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F")
        );
    }
}