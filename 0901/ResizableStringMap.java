import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap {

    static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private List<Entry>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public ResizableStringMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }

        buckets = new ArrayList[initialCapacity];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        size = 0;
    }

    private int index(String key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    public void put(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int index = index(key);

        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry(key, value));
        size++;

        if (loadFactor() > 0.75) {
            resize();
        }
    }

    public String get(String key) {
        if (key == null) {
            return null;
        }

        int index = index(key);

        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean containsKey(String key) {
        if (key == null) {
            return false;
        }

        int index = index(key);

        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean remove(String key) {
        if (key == null) {
            return false;
        }

        int index = index(key);

        for (int i = 0; i < buckets[index].size(); i++) {
            if (buckets[index].get(i).key.equals(key)) {
                buckets[index].remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public int bucketCount() {
        return buckets.length;
    }

    public double loadFactor() {
        return (double) size / buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        List<Entry>[] oldBuckets = buckets;

        int newCapacity = oldBuckets.length * 2 + 1;

        buckets = new ArrayList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (List<Entry> bucket : oldBuckets) {
            for (Entry entry : bucket) {
                int newIndex = index(entry.key);
                buckets[newIndex].add(entry);
            }
        }
    }

    public void bucketReport() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.println(
                    "bucket " + i + ": " + buckets[i]
            );
        }
    }

    public static void main(String[] args) {
        ResizableStringMap map =
                new ResizableStringMap(3);

        System.out.println(
                "Initial buckets: " + map.bucketCount()
        );

        map.put("A", "Apple");
        map.put("B", "Banana");

        System.out.println(
                "After 2 entries: buckets="
                + map.bucketCount()
        );

        map.put("C", "Cat");

        System.out.println(
                "After 3 entries: buckets="
                + map.bucketCount()
        );

        map.put("D", "Dog");
        map.put("E", "Egg");
        map.put("F", "Fish");

        System.out.println(
                "size: " + map.size()
        );

        System.out.println(
                "bucket count: " + map.bucketCount()
        );

        System.out.printf(
                "load factor: %.2f%n",
                map.loadFactor()
        );

        System.out.println(
                "get C: " + map.get("C")
        );

        System.out.println(
                "contains D: " + map.containsKey("D")
        );

        map.put("C", "Updated Cat");

        System.out.println(
                "updated C: " + map.get("C")
        );

        System.out.println(
                "size after update: " + map.size()
        );

        System.out.println(
                "remove B: " + map.remove("B")
        );

        System.out.println(
                "size after remove: " + map.size()
        );

        System.out.println();

        map.bucketReport();
    }
}