import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    private static int passed = 0;
    private static int failed = 0;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
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
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public boolean contains(int value) {
        Node current = root;

        while (current != null) {
            if (value == current.value) {
                return true;
            }

            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public boolean remove(int value) {
        if (!contains(value)) {
            return false;
        }

        root = remove(root, value);
        size--;
        return true;
    }

    private Node remove(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }

        return node;
    }

    private Node findMin(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    public List<Integer> range(int low, int high) {
        List<Integer> result = new ArrayList<>();

        if (low > high) {
            return result;
        }

        range(root, low, high, result);
        return result;
    }

    private void range(
            Node node,
            int low,
            int high,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        if (node.value > low) {
            range(node.left, low, high, result);
        }

        if (node.value >= low && node.value <= high) {
            result.add(node.value);
        }

        if (node.value < high) {
            range(node.right, low, high, result);
        }
    }

    public boolean isValid() {
        return isValid(root, null, null);
    }

    private boolean isValid(
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

        return isValid(node.left, low, node.value)
                && isValid(node.right, node.value, high);
    }

    public static void check(
            String description,
            boolean condition) {

        if (condition) {
            System.out.println("PASS: " + description);
            passed++;
        } else {
            System.out.println("FAIL: " + description);
            failed++;
        }
    }

    public static void main(String[] args) {

        CompleteBstTestSuite tree =
                new CompleteBstTestSuite();

        check("empty size is 0",
                tree.size() == 0);

        check("empty height is 0",
                tree.height() == 0);

        check("empty search is false",
                !tree.contains(50));

        check("remove from empty is false",
                !tree.remove(50));

        check("empty tree is valid",
                tree.isValid());

        check("add root 50",
                tree.add(50));

        check("root can be found",
                tree.contains(50));

        check("size after root is 1",
                tree.size() == 1);

        check("duplicate root rejected",
                !tree.add(50));

        check("size unchanged after duplicate",
                tree.size() == 1);

        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);
        tree.add(65);

        check("size after additions is 8",
                tree.size() == 8);

        check("leaf 20 exists",
                tree.contains(20));

        check("internal node 70 exists",
                tree.contains(70));

        check("missing 999 not found",
                !tree.contains(999));

        check("inorder sorted",
                tree.inorder().equals(
                        List.of(
                                20, 30, 40, 50,
                                60, 65, 70, 80
                        )
                ));

        check("tree invariant valid",
                tree.isValid());

        check("range 30 to 70",
                tree.range(30, 70).equals(
                        List.of(30, 40, 50, 60, 65, 70)
                ));

        check("range includes endpoints",
                tree.range(20, 40).equals(
                        List.of(20, 30, 40)
                ));

        check("empty range",
                tree.range(100, 200).isEmpty());

        check("low greater than high",
                tree.range(70, 30).isEmpty());

        check("remove missing returns false",
                !tree.remove(999));

        check("remove leaf 20",
                tree.remove(20));

        check("leaf 20 removed",
                !tree.contains(20));

        check("valid after leaf delete",
                tree.isValid());

        check("remove one-child node 60",
                tree.remove(60));

        check("child 65 still exists",
                tree.contains(65));

        check("valid after one-child delete",
                tree.isValid());

        check("remove two-child node 70",
                tree.remove(70));

        check("70 no longer exists",
                !tree.contains(70));

        check("valid after two-child delete",
                tree.isValid());

        CompleteBstTestSuite rootTree =
                new CompleteBstTestSuite();

        rootTree.add(50);
        rootTree.add(30);
        rootTree.add(70);

        check("remove root with two children",
                rootTree.remove(50));

        check("root tree size becomes 2",
                rootTree.size() == 2);

        check("root tree remains valid",
                rootTree.isValid());

        System.out.println();
        System.out.println("=== Test Summary ===");
        System.out.println("PASS = " + passed);
        System.out.println("FAIL = " + failed);
        System.out.println("TOTAL = " + (passed + failed));
    }
}