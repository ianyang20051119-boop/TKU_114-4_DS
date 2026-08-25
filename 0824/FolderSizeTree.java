public class FolderSizeTree {

    static class FolderNode {
        String name;
        int ownSize;
        FolderNode left;
        FolderNode right;

        FolderNode(String name, int ownSize) {
            this.name = name;
            this.ownSize = ownSize;
        }
    }

    static class SubtreeInfo {
        String name;
        int size;

        SubtreeInfo(String name, int size) {
            this.name = name;
            this.size = size;
        }
    }

    private static SubtreeInfo maximumSubtree;

    public static int calculateSubtreeSize(FolderNode node) {
        if (node == null) {
            return 0;
        }

        int leftSize = calculateSubtreeSize(node.left);
        int rightSize = calculateSubtreeSize(node.right);

        int totalSize = node.ownSize + leftSize + rightSize;

        if (maximumSubtree == null || totalSize > maximumSubtree.size) {
            maximumSubtree = new SubtreeInfo(node.name, totalSize);
        }

        return totalSize;
    }

    public static void printLeafFolders(FolderNode node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.println(node.name + "：" + node.ownSize);
            return;
        }

        printLeafFolders(node.left);
        printLeafFolders(node.right);
    }

    public static void main(String[] args) {

        FolderNode root = new FolderNode("Root", 100);

        root.left = new FolderNode("Documents", 200);
        root.right = new FolderNode("Pictures", 300);

        root.left.left = new FolderNode("Homework", 150);
        root.left.right = new FolderNode("Reports", 100);

        root.right.left = new FolderNode("Photos", 500);
        root.right.right = new FolderNode("Screenshots", 200);

        maximumSubtree = null;

        int totalSize = calculateSubtreeSize(root);

        System.out.println("總大小：" + totalSize);
        System.out.println("最大 subtree：" 
                + maximumSubtree.name + " = " + maximumSubtree.size);

        System.out.println("Leaf folder：");
        printLeafFolders(root);
    }
}