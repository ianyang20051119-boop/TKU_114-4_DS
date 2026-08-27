public class BstRangeReport {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
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
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public Integer min() {
        if (root == null) {
            return null;
        }

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    public Integer max() {
        if (root == null) {
            return null;
        }

        Node current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    public void printRange(int low, int high) {
        System.out.print("Range [" + low + ", " + high + "]: ");

        if (low > high) {
            System.out.println();
            return;
        }

        printRange(root, low, high);
        System.out.println();
    }

    private void printRange(Node node, int low, int high) {
        if (node == null) {
            return;
        }

        // 左子樹可能還有範圍內的值
        if (node.value > low) {
            printRange(node.left, low, high);
        }

        // 包含 low 與 high 端點
        if (node.value >= low && node.value <= high) {
            System.out.print(node.value + " ");
        }

        // 右子樹可能還有範圍內的值
        if (node.value < high) {
            printRange(node.right, low, high);
        }
    }

    public static void main(String[] args) {
        BstRangeReport tree = new BstRangeReport();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80,
            10, 25, 65, 90
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("=== BST Range Report ===");

        System.out.println("Min: " + tree.min());
        System.out.println("Max: " + tree.max());

        // 一般範圍
        tree.printRange(30, 70);

        // 測試包含端點
        tree.printRange(20, 60);

        // 沒有符合資料
        tree.printRange(100, 200);

        // low > high
        tree.printRange(70, 30);
    }
}