public class BinaryTreeStructureReport {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class BinaryTree {
        private Node root;

        public void setRoot(Node root) {
            this.root = root;
        }

        public Node getRoot() {
            return root;
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + size(node.left) + size(node.right);
        }

        public int leafCount() {
            return leafCount(root);
        }

        private int leafCount(Node node) {
            if (node == null) {
                return 0;
            }

            if (node.left == null && node.right == null) {
                return 1;
            }

            return leafCount(node.left) + leafCount(node.right);
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + Math.max(height(node.left), height(node.right));
        }

        public void printLeaves() {
            printLeaves(root);
            System.out.println();
        }

        private void printLeaves(Node node) {
            if (node == null) {
                return;
            }

            if (node.left == null && node.right == null) {
                System.out.print(node.value + " ");
                return;
            }

            printLeaves(node.left);
            printLeaves(node.right);
        }

        public void printReport() {
            if (root == null) {
                System.out.println("Root: null");
                System.out.println("Leaves: none");
                System.out.println("Size: 0");
                System.out.println("Leaf count: 0");
                System.out.println("Height: 0");
                return;
            }

            System.out.println("Root: " + root.value);
            System.out.print("Leaves: ");
            printLeaves();
            System.out.println("Size: " + size());
            System.out.println("Leaf count: " + leafCount());
            System.out.println("Height: " + height());
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Empty Tree ===");

        BinaryTree emptyTree = new BinaryTree();
        emptyTree.printReport();

        System.out.println();

        System.out.println("=== Single Node Tree ===");

        BinaryTree singleTree = new BinaryTree();
        singleTree.setRoot(new Node(10));
        singleTree.printReport();

        System.out.println();

        System.out.println("=== Seven Node Tree ===");

        BinaryTree tree = new BinaryTree();

        Node root = new Node(50);
        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);

        tree.setRoot(root);

        tree.printReport();
    }
}