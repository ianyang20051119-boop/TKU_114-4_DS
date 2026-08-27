public class BstDuplicateCounter {

    static class Node {
        int key;
        int count;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.count = 1;
        }
    }

    private Node root;

    public void add(int key) {
        root = add(root, key);
    }

    private Node add(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = add(node.left, key);
        } else if (key > node.key) {
            node.right = add(node.right, key);
        } else {
            // 相同 key 不建立新 Node
            node.count++;
        }

        return node;
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);

        System.out.print(node.key + "(" + node.count + ") ");

        inorder(node.right);
    }

    public static void main(String[] args) {
        BstDuplicateCounter tree = new BstDuplicateCounter();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80,
            30, 50, 30, 70, 60, 60
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("=== BST Duplicate Counter ===");
        System.out.print("Inorder: ");
        tree.inorder();
    }
}