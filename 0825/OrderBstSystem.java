import java.util.ArrayList;
import java.util.List;

public class OrderBstSystem {

    static class Order {
        private String orderId;
        private String customer;
        private int amount;

        public Order(String orderId, String customer, int amount) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = Math.max(amount, 0);
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomer() {
            return customer;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = Math.max(amount, 0);
        }

        @Override
        public String toString() {
            return orderId + " " + customer + " $" + amount;
        }
    }

    static class Node {
        Order order;
        Node left;
        Node right;

        Node(Order order) {
            this.order = order;
        }
    }

    private Node root;
    private int size;

    public boolean add(Order order) {
        if (order == null || order.getOrderId() == null) {
            return false;
        }

        if (root == null) {
            root = new Node(order);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int compare =
                    order.getOrderId().compareTo(current.order.getOrderId());

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {
                if (current.left == null) {
                    current.left = new Node(order);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(order);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Order find(String orderId) {
        if (orderId == null) {
            return null;
        }

        Node current = root;

        while (current != null) {
            int compare =
                    orderId.compareTo(current.order.getOrderId());

            if (compare == 0) {
                return current.order;
            }

            if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateAmount(String orderId, int amount) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        order.setAmount(amount);
        return true;
    }

    public boolean cancel(String orderId) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        root = delete(root, orderId);
        size--;

        return true;
    }

    private Node delete(Node node, String orderId) {
        if (node == null) {
            return null;
        }

        int compare =
                orderId.compareTo(node.order.getOrderId());

        if (compare < 0) {
            node.left = delete(node.left, orderId);
        } else if (compare > 0) {
            node.right = delete(node.right, orderId);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);

            node.order = successor.order;

            node.right = delete(
                    node.right,
                    successor.order.getOrderId()
            );
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

    public List<Order> rangeReport(String lowId, String highId) {
        List<Order> result = new ArrayList<>();

        if (lowId == null || highId == null) {
            return result;
        }

        if (lowId.compareTo(highId) > 0) {
            return result;
        }

        rangeReport(root, lowId, highId, result);

        return result;
    }

    private void rangeReport(
            Node node,
            String lowId,
            String highId,
            List<Order> result) {

        if (node == null) {
            return;
        }

        String id = node.order.getOrderId();

        if (id.compareTo(lowId) > 0) {
            rangeReport(node.left, lowId, highId, result);
        }

        if (id.compareTo(lowId) >= 0
                && id.compareTo(highId) <= 0) {
            result.add(node.order);
        }

        if (id.compareTo(highId) < 0) {
            rangeReport(node.right, lowId, highId, result);
        }
    }

    public List<Order> inorder() {
        List<Order> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Order> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.order);
        inorder(node.right, result);
    }

    public String summary() {
        int totalAmount = 0;

        for (Order order : inorder()) {
            totalAmount += order.getAmount();
        }

        return "size=" + size
                + ", totalAmount=" + totalAmount;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {

        OrderBstSystem system = new OrderBstSystem();

        system.add(new Order("O300", "Amy", 1200));
        system.add(new Order("O100", "Ben", 800));
        system.add(new Order("O500", "Cindy", 2500));
        system.add(new Order("O200", "David", 1500));
        system.add(new Order("O400", "Eva", 900));

        System.out.println("Inorder:");
        for (Order order : system.inorder()) {
            System.out.println(order);
        }

        System.out.println();

        System.out.println(
                "Find O200: " + system.find("O200")
        );

        System.out.println(
                "Find O999: " + system.find("O999")
        );

        System.out.println();

        System.out.println(
                "Update O200: "
                        + system.updateAmount("O200", 1800)
        );

        System.out.println(
                "O200: " + system.find("O200")
        );

        System.out.println();

        System.out.println("Range O200 ~ O400:");

        for (Order order :
                system.rangeReport("O200", "O400")) {
            System.out.println(order);
        }

        System.out.println();

        System.out.println(
                "Cancel O300: "
                        + system.cancel("O300")
        );

        System.out.println(
                "Cancel O999: "
                        + system.cancel("O999")
        );

        System.out.println();

        System.out.println("After cancel:");

        for (Order order : system.inorder()) {
            System.out.println(order);
        }

        System.out.println();

        System.out.println(system.summary());
    }
}