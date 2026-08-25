import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrganizationTreeReport {

    static class Node {
        String name;
        Node left;
        Node right;

        Node(String name) {
            this.name = name;
        }
    }

    public static Node findParent(Node root, String target) {
        if (root == null || target == null) {
            return null;
        }

        if ((root.left != null && root.left.name.equals(target))
                || (root.right != null && root.right.name.equals(target))) {
            return root;
        }

        Node parent = findParent(root.left, target);

        if (parent != null) {
            return parent;
        }

        return findParent(root.right, target);
    }

    public static int findDepth(Node root, String target) {
        return findDepth(root, target, 0);
    }

    private static int findDepth(Node node, String target, int depth) {
        if (node == null || target == null) {
            return -1;
        }

        if (node.name.equals(target)) {
            return depth;
        }

        int leftDepth = findDepth(node.left, target, depth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        }

        return findDepth(node.right, target, depth + 1);
    }

    public static List<String> pathFromRoot(Node root, String target) {
        List<String> path = new ArrayList<>();
        findPath(root, target, path);
        return path;
    }

    private static boolean findPath(
            Node node,
            String target,
            List<String> path) {

        if (node == null || target == null) {
            return false;
        }

        path.add(node.name);

        if (node.name.equals(target)) {
            return true;
        }

        if (findPath(node.left, target, path)
                || findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void printByLevel(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            System.out.print("Level " + level + ": ");

            for (int i = 0; i < count; i++) {
                Node current = queue.poll();

                System.out.print(current.name + " ");

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {

        Node root = new Node("CEO");

        root.left = new Node("Engineering");
        root.right = new Node("Sales");

        root.left.left = new Node("Backend");
        root.left.right = new Node("Frontend");

        root.right.left = new Node("Domestic");
        root.right.right = new Node("International");

        root.left.left.left = new Node("Database");
        root.left.left.right = new Node("API");

        System.out.println("Parent of Backend: "
                + (findParent(root, "Backend") != null
                ? findParent(root, "Backend").name
                : "None"));

        System.out.println("Depth of API: "
                + findDepth(root, "API"));

        System.out.println("Path to API: "
                + pathFromRoot(root, "API"));

        System.out.println("Path to Unknown: "
                + pathFromRoot(root, "Unknown"));

        System.out.println("Depth of Unknown: "
                + findDepth(root, "Unknown"));

        System.out.println("Parent of Unknown: "
                + (findParent(root, "Unknown") == null
                ? "None"
                : findParent(root, "Unknown").name));

        System.out.println();
        printByLevel(root);

        System.out.println();
        System.out.println("Empty tree:");
        printByLevel(null);
    }
}