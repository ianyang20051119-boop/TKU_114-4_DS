import java.util.ArrayList;
import java.util.List;

public class ProductInventoryBst {

    static class Product {
        private String id;
        private String name;
        private int stock;

        public Product(String id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = Math.max(stock, 0);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getStock() {
            return stock;
        }

        public boolean restock(int amount) {
            if (amount <= 0) {
                return false;
            }

            stock += amount;
            return true;
        }

        public boolean reduceStock(int amount) {
            if (amount <= 0 || amount > stock) {
                return false;
            }

            stock -= amount;
            return true;
        }

        @Override
        public String toString() {
            return id + " " + name + " stock=" + stock;
        }
    }

    static class Node {
        Product product;
        Node left;
        Node right;

        Node(Product product) {
            this.product = product;
        }
    }

    private Node root;
    private int size;

    public boolean add(Product product) {
        if (product == null || product.getId() == null) {
            return false;
        }

        if (root == null) {
            root = new Node(product);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int compare = product.getId().compareTo(current.product.getId());

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {
                if (current.left == null) {
                    current.left = new Node(product);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(product);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Product find(String id) {
        if (id == null) {
            return null;
        }

        Node current = root;

        while (current != null) {
            int compare = id.compareTo(current.product.getId());

            if (compare == 0) {
                return current.product;
            }

            if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean restock(String id, int amount) {
        Product product = find(id);

        if (product == null) {
            return false;
        }

        return product.restock(amount);
    }

    public boolean reduceStock(String id, int amount) {
        Product product = find(id);

        if (product == null) {
            return false;
        }

        return product.reduceStock(amount);
    }

    public boolean delete(String id) {
        Product product = find(id);

        if (product == null) {
            return false;
        }

        root = delete(root, product.getId());
        size--;
        return true;
    }

    private Node delete(Node node, String id) {
        if (node == null) {
            return null;
        }

        int compare = id.compareTo(node.product.getId());

        if (compare < 0) {
            node.left = delete(node.left, id);
        } else if (compare > 0) {
            node.right = delete(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.product = successor.product;
            node.right = delete(
                    node.right,
                    successor.product.getId()
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

    public int size() {
        return size;
    }

    public List<Product> inorderReport() {
        List<Product> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Product> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.product);
        inorder(node.right, result);
    }

    public static void main(String[] args) {
        ProductInventoryBst inventory = new ProductInventoryBst();

        System.out.println(inventory.add(
                new Product("P300", "Keyboard", 10)
        ));

        System.out.println(inventory.add(
                new Product("P100", "Mouse", 20)
        ));

        System.out.println(inventory.add(
                new Product("P500", "Monitor", 5)
        ));

        System.out.println(inventory.add(
                new Product("P200", "USB Cable", 15)
        ));

        System.out.println(inventory.add(
                new Product("P400", "Headset", 8)
        ));

        System.out.println("Find P200: "
                + inventory.find("P200"));

        System.out.println("Restock P200: "
                + inventory.restock("P200", 5));

        System.out.println("Reduce P100: "
                + inventory.reduceStock("P100", 3));

        System.out.println("Delete P300: "
                + inventory.delete("P300"));

        System.out.println("Size: " + inventory.size());

        System.out.println("Inorder Report:");

        for (Product product : inventory.inorderReport()) {
            System.out.println(product);
        }
    }
}