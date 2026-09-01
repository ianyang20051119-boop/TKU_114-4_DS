import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {

    static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private final List<Entry>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public IntegerStringHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }

        buckets = new ArrayList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
        }

        size = 0;
    }

    private int index(int key) {
        return Math.floorMod(key, buckets.length);
    }

    public void put(int key, String value) {
        int bucketIndex = index(key);

        for (Entry entry : buckets[bucketIndex]) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        buckets[bucketIndex].add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int bucketIndex = index(key);

        for (Entry entry : buckets[bucketIndex]) {
            if (entry.key == key) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean containsKey(int key) {
        int bucketIndex = index(key);

        for (Entry entry : buckets[bucketIndex]) {
            if (entry.key == key) {
                return true;
            }
        }

        return false;
    }

    public boolean remove(int key) {
        int bucketIndex = index(key);

        for (int i = 0; i < buckets[bucketIndex].size(); i++) {
            if (buckets[bucketIndex].get(i).key == key) {
                buckets[bucketIndex].remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.println(
                "bucket " + i + ": " + buckets[i]
            );
        }
    }

    public static void main(String[] args) {
        IntegerStringHashTable table =
                new IntegerStringHashTable(5);

        table.put(10, "Apple");
        table.put(15, "Banana");
        table.put(7, "Orange");
        table.put(12, "Grape");
        table.put(-5, "Mango");
        table.put(20, "Melon");

        System.out.println("Initial:");
        table.bucketReport();

        System.out.println("size: " + table.size());

        System.out.println();
        System.out.println("get 10: " + table.get(10));
        System.out.println(
            "contains 15: " + table.containsKey(15)
        );
        System.out.println(
            "contains 99: " + table.containsKey(99)
        );

        System.out.println();
        System.out.println("Update key 10");
        table.put(10, "Updated Apple");

        System.out.println("get 10: " + table.get(10));
        System.out.println(
            "size after update: " + table.size()
        );

        System.out.println();
        System.out.println(
            "remove 15: " + table.remove(15)
        );
        System.out.println(
            "remove 99: " + table.remove(99)
        );

        System.out.println("size: " + table.size());

        System.out.println();
        System.out.println("Final:");
        table.bucketReport();
    }
}