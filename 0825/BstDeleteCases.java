import java.util.ArrayList;
import java.util.List;

public class BstDeleteCases {

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

            // Case 1: leaf
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: single child
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            // Case 3: two children
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

    public int size() {
        return size;
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

    private boolean isValid(Node node, Integer min, Integer max) {
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

    private void printStatus(String title) {
        System.out.println(title);
        System.out.println("inorder = " + inorder());
        System.out.println("size = " + size());
        System.out.println("valid = " + isValid());
        System.out.println();
    }

    public static void main(String[] args) {
        BstDeleteCases tree = new BstDeleteCases();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80, 65
        };

        for (int value : values) {
            tree.add(value);
        }

        tree.printStatus("Initial tree");

        // leaf node: 20
        tree.remove(20);
        tree.printStatus("After deleting leaf 20");

        // single-child node: 60
        // 60 has one child: 65
        tree.remove(60);
        tree.printStatus("After deleting single-child node 60");

        // two-child node: 70
        // 70 has left child 65 and right child 80
        tree.remove(70);
        tree.printStatus("After deleting two-child node 70");
    }
}