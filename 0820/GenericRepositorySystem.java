import java.util.ArrayList;
import java.util.List;

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + price;
    }
}

class Repository<T> {
    private ArrayList<T> data = new ArrayList<>();

    public void add(T item) {
        data.add(item);
    }

    public T get(int index) {
        if (index < 0 || index >= data.size()) {
            return null;
        }
        return data.get(index);
    }

    public T remove(int index) {
        if (index < 0 || index >= data.size()) {
            return null;
        }
        return data.remove(index);
    }

    public int size() {
        return data.size();
    }

    public void printAll() {
        for (T item : data) {
            System.out.println(item);
        }
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        Repository<String> stringRepository = new Repository<>();

        stringRepository.add("Java");
        stringRepository.add("C++");
        stringRepository.add("Python");

        System.out.println("String Repository：");
        stringRepository.printAll();
        System.out.println("取得：" + stringRepository.get(1));
        System.out.println("大小：" + stringRepository.size());

        stringRepository.remove(1);

        System.out.println("移除後：");
        stringRepository.printAll();
        System.out.println("大小：" + stringRepository.size());

        System.out.println();

        Repository<Product> productRepository = new Repository<>();

        productRepository.add(new Product("P001", "Keyboard", 800));
        productRepository.add(new Product("P002", "Mouse", 500));
        productRepository.add(new Product("P003", "Monitor", 3000));

        System.out.println("Product Repository：");
        productRepository.printAll();
        System.out.println("取得：" + productRepository.get(1));
        System.out.println("大小：" + productRepository.size());

        productRepository.remove(1);

        System.out.println("移除後：");
        productRepository.printAll();
        System.out.println("大小：" + productRepository.size());
    }
}