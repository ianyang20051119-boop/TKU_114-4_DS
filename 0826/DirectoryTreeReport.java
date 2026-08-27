public class DirectoryTreeReport {

    static class Node {
        String name;
        boolean directory;
        int size;
        Node left;
        Node right;

        Node(String name, boolean directory, int size) {
            this.name = name;
            this.directory = directory;
            this.size = directory ? 0 : Math.max(size, 0);
        }
    }

    public static int calculateDirectorySize(Node node) {
        if (node == null) {
            return 0;
        }

        int leftSize = calculateDirectorySize(node.left);
        int rightSize = calculateDirectorySize(node.right);

        if (node.directory) {
            int total = leftSize + rightSize;

            System.out.println(
                    "Directory " + node.name
                            + " total size = " + total
            );

            return total;
        }

        return node.size + leftSize + rightSize;
    }

    public static int totalNodes(Node node) {
        if (node == null) {
            return 0;
        }

        return 1
                + totalNodes(node.left)
                + totalNodes(node.right);
    }

    public static int fileCount(Node node) {
        if (node == null) {
            return 0;
        }

        int current = node.directory ? 0 : 1;

        return current
                + fileCount(node.left)
                + fileCount(node.right);
    }

    public static int directoryCount(Node node) {
        if (node == null) {
            return 0;
        }

        int current = node.directory ? 1 : 0;

        return current
                + directoryCount(node.left)
                + directoryCount(node.right);
    }

    public static int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    public static Node largestFile(Node node) {
        if (node == null) {
            return null;
        }

        Node largest = node.directory ? null : node;

        Node leftLargest = largestFile(node.left);
        Node rightLargest = largestFile(node.right);

        largest = largerFile(largest, leftLargest);
        largest = largerFile(largest, rightLargest);

        return largest;
    }

    private static Node largerFile(Node a, Node b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        if (b.size > a.size) {
            return b;
        }

        return a;
    }

    public static void main(String[] args) {
        Node root = new Node("root", true, 0);

        root.left = new Node("documents", true, 0);
        root.right = new Node("media", true, 0);

        root.left.left = new Node(
                "report.pdf", false, 120
        );

        root.left.right = new Node(
                "notes.txt", false, 40
        );

        root.right.left = new Node(
                "photos", true, 0
        );

        root.right.right = new Node(
                "movie.mp4", false, 1500
        );

        root.right.left.left = new Node(
                "photo1.jpg", false, 300
        );

        root.right.left.right = new Node(
                "photo2.jpg", false, 450
        );

        System.out.println(
                "=== Directory Size Report ==="
        );

        int totalSize = calculateDirectorySize(root);

        System.out.println();

        Node largest = largestFile(root);

        System.out.println(
                "Root total size = " + totalSize
        );

        System.out.println(
                "Total node = " + totalNodes(root)
        );

        System.out.println(
                "File count = " + fileCount(root)
        );

        System.out.println(
                "Directory count = " + directoryCount(root)
        );

        System.out.println(
                "Height = " + height(root)
        );

        if (largest != null) {
            System.out.println(
                    "Largest file = "
                            + largest.name
                            + " (" + largest.size + ")"
            );
        } else {
            System.out.println("Largest file = NONE");
        }
    }
}