import java.util.ArrayList;
import java.util.List;

public class BstDeleteTestSuite {

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

    public boolean isEmpty() {
        return root == null;
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

    public boolean isValid() {
        return isValid(root, null, null);
    }

    private boolean isValid(Node node, Integer low, Integer high) {
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

    private void printResult(String testName, boolean result) {
        System.out.println("=== " + testName + " ===");
        System.out.println("remove result = " + result);
        System.out.println("inorder = " + inorder());
        System.out.println("size = " + size());
        System.out.println("valid = " + isValid());
        System.out.println("empty = " + isEmpty());
        System.out.println();
    }

    public static void main(String[] args) {

        BstDeleteTestSuite emptyTree = new BstDeleteTestSuite();

        boolean result1 = emptyTree.remove(50);
        emptyTree.printResult(
                "Delete From Empty Tree",
                result1
        );


        BstDeleteTestSuite missingTree = new BstDeleteTestSuite();

        missingTree.add(50);
        missingTree.add(30);
        missingTree.add(70);

        boolean result2 = missingTree.remove(99);
        missingTree.printResult(
                "Delete Missing Value",
                result2
        );


        BstDeleteTestSuite singleRootTree =
                new BstDeleteTestSuite();

        singleRootTree.add(50);

        boolean result3 = singleRootTree.remove(50);
        singleRootTree.printResult(
                "Delete Single Root",
                result3
        );


        BstDeleteTestSuite oneChildTree =
                new BstDeleteTestSuite();

        oneChildTree.add(50);
        oneChildTree.add(30);

        boolean result4 = oneChildTree.remove(50);
        oneChildTree.printResult(
                "Delete Root With One Child",
                result4
        );


        BstDeleteTestSuite twoChildrenTree =
                new BstDeleteTestSuite();

        twoChildrenTree.add(50);
        twoChildrenTree.add(30);
        twoChildrenTree.add(70);
        twoChildrenTree.add(60);
        twoChildrenTree.add(80);

        boolean result5 = twoChildrenTree.remove(50);
        twoChildrenTree.printResult(
                "Delete Root With Two Children",
                result5
        );


        BstDeleteTestSuite continuousTree =
                new BstDeleteTestSuite();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            continuousTree.add(value);
        }

        System.out.println(
                "=== Continuous Delete Until Empty ==="
        );

        int[] deleteOrder = {
            20, 30, 50, 70, 40, 60, 80
        };

        for (int value : deleteOrder) {
            boolean result = continuousTree.remove(value);

            System.out.println(
                    "remove " + value + " = " + result
            );
            System.out.println(
                    "inorder = " + continuousTree.inorder()
            );
            System.out.println(
                    "size = " + continuousTree.size()
            );
            System.out.println(
                    "valid = " + continuousTree.isValid()
            );
            System.out.println();
        }

        System.out.println(
                "final empty = " + continuousTree.isEmpty()
        );
    }
}