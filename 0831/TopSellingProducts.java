import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {

    static class Product {
        private String id;
        private int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        public String getId() {
            return id;
        }

        public int getSales() {
            return sales;
        }

        @Override
        public String toString() {
            return id + "|" + sales;
        }
    }

    public static List<Product> topK(
            List<Product> products, int k) {

        List<Product> result = new ArrayList<>();

        if (products == null || k <= 0) {
            return result;
        }

        Map<String, Integer> merged = new HashMap<>();

        for (Product product : products) {
            if (product == null || product.getId() == null) {
                continue;
            }

            merged.put(
                product.getId(),
                merged.getOrDefault(product.getId(), 0)
                        + product.getSales()
            );
        }

        PriorityQueue<Product> heap =
                new PriorityQueue<>((a, b) -> {
                    if (a.getSales() != b.getSales()) {
                        return Integer.compare(
                                a.getSales(),
                                b.getSales()
                        );
                    }

                    return b.getId().compareTo(a.getId());
                });

        for (Map.Entry<String, Integer> entry
                : merged.entrySet()) {

            Product product = new Product(
                    entry.getKey(),
                    entry.getValue()
            );

            heap.offer(product);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        result.addAll(heap);

        result.sort((a, b) -> {
            if (a.getSales() != b.getSales()) {
                return Integer.compare(
                        b.getSales(),
                        a.getSales()
                );
            }

            return a.getId().compareTo(b.getId());
        });

        return result;
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product("P103", 50));
        products.add(new Product("P101", 80));
        products.add(new Product("P105", 40));
        products.add(new Product("P102", 80));
        products.add(new Product("P104", 60));

        products.add(new Product("P103", 40));
        products.add(new Product("P105", 50));
        products.add(new Product("P101", 20));

        int k = 3;

        List<Product> result = topK(products, k);

        System.out.println("Top " + k + ":");

        for (Product product : result) {
            System.out.println(product);
        }
    }
}