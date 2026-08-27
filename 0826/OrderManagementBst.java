import java.util.ArrayList;
import java.util.List;

public class OrderManagementBst {

    static class Order {
        private String orderId;
        private String customer;
        private int amount;
        private String status;

        public Order(String orderId, String customer, int amount, String status) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = Math.max(amount, 0);
            this.status = status;
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

        public String getStatus() {
            return status;
        }

        public boolean setStatus(String status) {
            if (status == null || status.trim().isEmpty()) {
                return false;
            }

            this.status = status.trim();
            return true;
        }

        @Override
        public String toString() {
            return orderId + " " + customer
                    + " amount=" + amount
                    + " status=" + status;
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
            int compare = order.getOrderId()
                    .compareTo(current.order.getOrderId());

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
            int compare = orderId.compareTo(
                    current.order.getOrderId()
            );

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

    public boolean updateStatus(String orderId, String status) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        return order.setStatus(status);
    }

    public boolean cancel(String orderId) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if ("CANCELLED".equals(order.getStatus())) {
            return false;
        }

        order.setStatus("CANCELLED");
        return true;
    }

    public boolean remove(String orderId) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if (!"CANCELLED".equals(order.getStatus())) {
            return false;
        }

        root = remove(root, orderId);
        size--;
        return true;
    }

    private Node remove(Node node, String orderId) {
        if (node == null) {
            return null;
        }

        int compare = orderId.compareTo(
                node.order.getOrderId()
        );

        if (compare < 0) {
            node.left = remove(node.left, orderId);
        } else if (compare > 0) {
            node.right = remove(node.right, orderId);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.order = successor.order;

            node.right = remove(
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

    public List<Order> idRangeReport(
            String lowId,
            String highId) {

        List<Order> result = new ArrayList<>();

        if (lowId == null || highId == null) {
            return result;
        }

        if (lowId.compareTo(highId) > 0) {
            return result;
        }

        idRangeReport(root, lowId, highId, result);
        return result;
    }

    private void idRangeReport(
            Node node,
            String lowId,
            String highId,
            List<Order> result) {

        if (node == null) {
            return;
        }

        String id = node.order.getOrderId();

        if (id.compareTo(lowId) > 0) {
            idRangeReport(
                    node.left,
                    lowId,
                    highId,
                    result
            );
        }

        if (id.compareTo(lowId) >= 0
                && id.compareTo(highId) <= 0) {
            result.add(node.order);
        }

        if (id.compareTo(highId) < 0) {
            idRangeReport(
                    node.right,
                    lowId,
                    highId,
                    result
            );
        }
    }

    public List<Order> inorderReport() {
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

    public int totalAmount() {
        return totalAmount(root);
    }

    private int totalAmount(Node node) {
        if (node == null) {
            return 0;
        }

        return node.order.getAmount()
                + totalAmount(node.left)
                + totalAmount(node.right);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        OrderManagementBst system = new OrderManagementBst();

        System.out.println(system.add(
                new Order("O300", "Amy", 1200, "NEW")
        ));

        System.out.println(system.add(
                new Order("O100", "Ben", 800, "PAID")
        ));

        System.out.println(system.add(
                new Order("O500", "Cindy", 2500, "NEW")
        ));

        System.out.println(system.add(
                new Order("O200", "David", 1500, "SHIPPED")
        ));

        System.out.println(system.add(
                new Order("O400", "Eva", 900, "NEW")
        ));

        System.out.println(system.add(
                new Order("O300", "Frank", 999, "NEW")
        ));

        System.out.println();

        System.out.println(
                "Find O200: " + system.find("O200")
        );

        System.out.println(
                "Find O999: " + system.find("O999")
        );

        System.out.println();

        System.out.println(
                "Update O400: "
                        + system.updateStatus("O400", "PAID")
        );

        System.out.println(
                "O400: " + system.find("O400")
        );

        System.out.println();

        System.out.println(
                "Remove O100 before cancel: "
                        + system.remove("O100")
        );

        System.out.println(
                "Cancel O100: "
                        + system.cancel("O100")
        );

        System.out.println(
                "Remove O100 after cancel: "
                        + system.remove("O100")
        );

        System.out.println();

        System.out.println("Range O200 ~ O500:");

        for (Order order :
                system.idRangeReport("O200", "O500")) {
            System.out.println(order);
        }

        System.out.println();

        System.out.println("Inorder Report:");

        for (Order order : system.inorderReport()) {
            System.out.println(order);
        }

        System.out.println();

        System.out.println(
                "Total amount = " + system.totalAmount()
        );

        System.out.println(
                "Size = " + system.size()
        );
    }
}