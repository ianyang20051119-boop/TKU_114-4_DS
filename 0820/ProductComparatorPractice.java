import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    private String id;
    private String name;
    private double price;
    private int stock;

    public StoreProduct(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public int compareTo(StoreProduct other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id + " " + name + " $" + price + " stock=" + stock;
    }
}

public class ProductComparatorPractice {

    static Comparator<StoreProduct> priceComparator =
            Comparator.comparingDouble(StoreProduct::getPrice)
                      .thenComparing(StoreProduct::getName);

    static Comparator<StoreProduct> stockComparator =
            Comparator.comparingInt(StoreProduct::getStock)
                      .reversed()
                      .thenComparing(StoreProduct::getId);

    public static void main(String[] args) {
        List<StoreProduct> products = new ArrayList<>();

        products.add(new StoreProduct("P103", "Mouse", 500, 20));
        products.add(new StoreProduct("P101", "Keyboard", 800, 15));
        products.add(new StoreProduct("P105", "Monitor", 500, 30));
        products.add(new StoreProduct("P102", "Headset", 800, 20));
        products.add(new StoreProduct("P104", "Webcam", 1200, 30));

        System.out.println("原始順序：");
        products.forEach(System.out::println);

        List<StoreProduct> naturalOrder = new ArrayList<>(products);
        naturalOrder.sort(null);

        System.out.println("\nNatural Order（id 升冪）：");
        naturalOrder.forEach(System.out::println);

        List<StoreProduct> priceOrder = new ArrayList<>(products);
        priceOrder.sort(priceComparator);

        System.out.println("\nPrice Comparator（price 升冪，同價依 name）：");
        priceOrder.forEach(System.out::println);

        List<StoreProduct> stockOrder = new ArrayList<>(products);
        stockOrder.sort(stockComparator);

        System.out.println("\nStock Comparator（stock 降冪，同庫存依 id）：");
        stockOrder.forEach(System.out::println);

        System.out.println("\n確認原始順序：");
        products.forEach(System.out::println);
    }
}