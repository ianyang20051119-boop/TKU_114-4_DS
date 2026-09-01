import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntegerMinHeap {

    private final List<Integer> heap = new ArrayList<>();

    public void add(int value) {
        heap.add(value);

        int index = heap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(parent) <= heap.get(index)) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return heap.get(0);
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < heap.size()
                    && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < heap.size()
                    && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        IntegerMinHeap heap = new IntegerMinHeap();

        int[] values = {40, 15, 30, 10, 25, 50, 15};

        for (int value : values) {
            heap.add(value);
        }

        System.out.println("size: " + heap.size());
        System.out.println("peek: " + heap.peek());

        int previous = Integer.MIN_VALUE;
        boolean nonDecreasing = true;

        System.out.print("remove order: ");

        while (!heap.isEmpty()) {
            int current = heap.removeMin();
            System.out.print(current + " ");

            if (current < previous) {
                nonDecreasing = false;
            }

            previous = current;
        }

        System.out.println();
        System.out.println("non-decreasing: " + nonDecreasing);
        System.out.println("isEmpty: " + heap.isEmpty());

        try {
            heap.peek();
        } catch (NoSuchElementException e) {
            System.out.println("empty peek: NoSuchElementException");
        }

        try {
            heap.removeMin();
        } catch (NoSuchElementException e) {
            System.out.println("empty removeMin: NoSuchElementException");
        }
    }
}