import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {

    private final List<Integer> heap = new ArrayList<>();

    public void add(int value) {
        heap.add(value);

        int index = heap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(parent) >= heap.get(index)) {
                break;
            }

            int temp = heap.get(parent);
            heap.set(parent, heap.get(index));
            heap.set(index, temp);

            index = parent;
        }
    }

    public Integer peekMax() {
        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace maxHeap = new MaxHeapInsertTrace();

        int[] values = {25, 40, 10, 50, 30, 50};

        for (int value : values) {
            maxHeap.add(value);
            System.out.println("add " + value + ": " + maxHeap.snapshot());
        }

        System.out.println("Max: " + maxHeap.peekMax());
        System.out.println("Root is 50: " + (maxHeap.peekMax() == 50));
    }
}