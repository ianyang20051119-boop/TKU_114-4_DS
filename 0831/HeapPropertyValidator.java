import java.util.ArrayList;
import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(List<Integer> heap) {
        if (heap == null) {
            return false;
        }

        for (int i = 0; i < heap.size() / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < heap.size()
                    && heap.get(i) > heap.get(left)) {
                return false;
            }

            if (right < heap.size()
                    && heap.get(i) > heap.get(right)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isMaxHeap(List<Integer> heap) {
        if (heap == null) {
            return false;
        }

        for (int i = 0; i < heap.size() / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < heap.size()
                    && heap.get(i) < heap.get(left)) {
                return false;
            }

            if (right < heap.size()
                    && heap.get(i) < heap.get(right)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<Integer> minHeap = new ArrayList<>();
        minHeap.add(10);
        minHeap.add(20);
        minHeap.add(15);
        minHeap.add(30);
        minHeap.add(25);

        List<Integer> maxHeap = new ArrayList<>();
        maxHeap.add(50);
        maxHeap.add(40);
        maxHeap.add(45);
        maxHeap.add(20);
        maxHeap.add(30);

        List<Integer> invalid = new ArrayList<>();
        invalid.add(10);
        invalid.add(50);
        invalid.add(20);

        List<Integer> empty = new ArrayList<>();

        List<Integer> single = new ArrayList<>();
        single.add(100);

        System.out.println("minHeap isMinHeap: "
                + isMinHeap(minHeap));

        System.out.println("minHeap isMaxHeap: "
                + isMaxHeap(minHeap));

        System.out.println("maxHeap isMaxHeap: "
                + isMaxHeap(maxHeap));

        System.out.println("maxHeap isMinHeap: "
                + isMinHeap(maxHeap));

        System.out.println("invalid isMinHeap: "
                + isMinHeap(invalid));

        System.out.println("empty min: "
                + isMinHeap(empty));

        System.out.println("empty max: "
                + isMaxHeap(empty));

        System.out.println("single min: "
                + isMinHeap(single));

        System.out.println("single max: "
                + isMaxHeap(single));

        System.out.println("null min: "
                + isMinHeap(null));

        System.out.println("null max: "
                + isMaxHeap(null));
    }
}