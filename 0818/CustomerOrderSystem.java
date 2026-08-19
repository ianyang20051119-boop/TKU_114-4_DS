class Customer {

    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {

    private String productName;
    private double price;
    private int quantity;

    public OrderItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return productName + "，單價：" + price + "，數量：" + quantity;
    }
}

class CustomerOrder {

    private Customer customer;
    private OrderItem[] items;
    private int itemCount;

    public CustomerOrder(Customer customer, int capacity) {
        this.customer = customer;
        this.items = new OrderItem[capacity];
        this.itemCount = 0;
    }

    public boolean addItem(OrderItem item) {
        if (item == null || itemCount >= items.length) {
            return false;
        }

        items[itemCount] = item;
        itemCount++;
        return true;
    }

    public double getTotalAmount() {
        double total = 0;

        for (int i = 0; i < itemCount; i++) {
            total += items[i].getTotal();
        }

        return total;
    }

    public int getItemQuantity() {
        int total = 0;

        for (int i = 0; i < itemCount; i++) {
            total += items[i].getQuantity();
        }

        return total;
    }

    public String summary() {
        String result = "顧客編號：" + customer.getId()
                + "\n顧客姓名：" + customer.getName()
                + "\n品項：\n";

        for (int i = 0; i < itemCount; i++) {
            result += items[i] + "\n";
        }

        result += "品項數量：" + getItemQuantity()
                + "\n訂單總額：" + getTotalAmount();

        return result;
    }
}

public class CustomerOrderSystem {

    public static void main(String[] args) {

        Customer customer = new Customer("C001", "王小明");

        CustomerOrder order = new CustomerOrder(customer, 3);

        order.addItem(new OrderItem("筆記本", 50, 2));
        order.addItem(new OrderItem("原子筆", 20, 3));
        order.addItem(new OrderItem("資料夾", 80, 1));

        System.out.println("=== 訂單摘要 ===");
        System.out.println(order.summary());
    }
}