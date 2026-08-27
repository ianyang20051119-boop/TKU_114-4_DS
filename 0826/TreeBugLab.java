import java.util.ArrayList;
import java.util.List;

public class TreeBugLab {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static boolean buggySearch(Node root, int target) {
        Node current = root;

        while (current != null) {
            if (target == current.value) {
                return true;
            }

            if (target < current.value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    public static boolean fixedSearch(Node root, int target) {
        Node current = root;

        while (current != null) {
            if (target == current.value) {
                return true;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public static List<Integer> buggyInorder(Node root) {
        List<Integer> result = new ArrayList<>();
        buggyInorder(root, result);
        return result;
    }

    private static void buggyInorder(
            Node node,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        result.add(node.value);
        buggyInorder(node.left, result);
        buggyInorder(node.right, result);
    }

    public static List<Integer> fixedInorder(Node root) {
        List<Integer> result = new ArrayList<>();
        fixedInorder(root, result);
        return result;
    }

    private static void fixedInorder(
            Node node,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        fixedInorder(node.left, result);
        result.add(node.value);
        fixedInorder(node.right, result);
    }

    public static Node buggyDelete(Node node, int target) {
        if (node == null) {
            return null;
        }

        if (target < node.value) {
            node.left = buggyDelete(node.left, target);
        } else if (target > node.value) {
            node.right = buggyDelete(node.right, target);
        } else {
            if (node.left == null) {
                return null;
            }

            if (node.right == null) {
                return null;
            }

            Node successor = findMin(node.right);
            node.value = successor.value;
            node.right = buggyDelete(
                    node.right,
                    successor.value
            );
        }

        return node;
    }

    public static Node fixedDelete(Node node, int target) {
        if (node == null) {
            return null;
        }

        if (target < node.value) {
            node.left = fixedDelete(node.left, target);
        } else if (target > node.value) {
            node.right = fixedDelete(node.right, target);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.value = successor.value;
            node.right = fixedDelete(
                    node.right,
                    successor.value
            );
        }

        return node;
    }

    private static Node findMin(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public static boolean buggyValidation(Node node) {
        if (node == null) {
            return true;
        }

        if (node.left != null
                && node.left.value >= node.value) {
            return false;
        }

        if (node.right != null
                && node.right.value <= node.value) {
            return false;
        }

        return buggyValidation(node.left)
                && buggyValidation(node.right);
    }

    public static boolean fixedValidation(Node root) {
        return fixedValidation(root, null, null);
    }

    private static boolean fixedValidation(
            Node node,
            Integer low,
            Integer high) {

        if (node == null) {
            return true;
        }

        if (low != null && node.value <= low) {
            return false;
        }

        if (high != null && node.value >= high) {
            return false;
        }

        return fixedValidation(
                node.left,
                low,
                node.value
        ) && fixedValidation(
                node.right,
                node.value,
                high
        );
    }

    private static void searchBugTest() {
        Node root = new Node(10);
        root.left = new Node(5);

        System.out.println(
                "=== Search Direction Bug ==="
        );

        System.out.println(
                "Target = 5"
        );

        System.out.println(
                "Buggy result = "
                        + buggySearch(root, 5)
        );

        System.out.println(
                "Fixed result = "
                        + fixedSearch(root, 5)
        );

        System.out.println();
    }

    private static void inorderBugTest() {
        Node root = new Node(10);
        root.left = new Node(5);

        System.out.println(
                "=== Inorder Order Bug ==="
        );

        System.out.println(
                "Expected = [5, 10]"
        );

        System.out.println(
                "Buggy result = "
                        + buggyInorder(root)
        );

        System.out.println(
                "Fixed result = "
                        + fixedInorder(root)
        );

        System.out.println();
    }

    private static void deleteBugTest() {
        Node buggyRoot = new Node(10);
        buggyRoot.right = new Node(20);

        Node fixedRoot = new Node(10);
        fixedRoot.right = new Node(20);

        buggyRoot = buggyDelete(buggyRoot, 10);
        fixedRoot = fixedDelete(fixedRoot, 10);

        System.out.println(
                "=== Delete Lost Child Bug ==="
        );

        System.out.println(
                "Expected = [20]"
        );

        System.out.println(
                "Buggy result = "
                        + fixedInorder(buggyRoot)
        );

        System.out.println(
                "Fixed result = "
                        + fixedInorder(fixedRoot)
        );

        System.out.println();
    }

    private static void validationBugTest() {
        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(15);

        root.left.right = new Node(12);

        System.out.println(
                "=== Validation Direct Child Bug ==="
        );

        System.out.println(
                "Expected = false"
        );

        System.out.println(
                "Buggy result = "
                        + buggyValidation(root)
        );

        System.out.println(
                "Fixed result = "
                        + fixedValidation(root)
        );

        System.out.println();
    }

    public static void main(String[] args) {
        searchBugTest();
        inorderBugTest();
        deleteBugTest();
        validationBugTest();
    }
}