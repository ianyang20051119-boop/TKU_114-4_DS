import java.util.ArrayList;
import java.util.List;

public class BstOperationAudit {

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

    private void audit(String operation, boolean result) {
        System.out.println("operation = " + operation);
        System.out.println("result = " + result);
        System.out.println("inorder = " + inorder());
        System.out.println("size = " + size());
        System.out.println("height = " + height());
        System.out.println("valid = " + isValid());
        System.out.println();
    }

    public void addAndAudit(int value) {
        boolean result = add(value);
        audit("add " + value, result);
    }

    public void removeAndAudit(int value) {
        boolean result = remove(value);
        audit("remove " + value, result);
    }

    public static void main(String[] args) {
        BstOperationAudit tree = new BstOperationAudit();

        tree.addAndAudit(50);
        tree.addAndAudit(30);
        tree.addAndAudit(70);
        tree.addAndAudit(20);
        tree.addAndAudit(40);
        tree.addAndAudit(60);
        tree.addAndAudit(80);
        tree.addAndAudit(65);

        tree.addAndAudit(40);

        tree.removeAndAudit(999);

        tree.removeAndAudit(20);

        tree.removeAndAudit(60);

        tree.removeAndAudit(70);
    }
}