import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {

    private int[] heap;
    private int size;

    public ArrayMinHeap() {
        heap = new int[4];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity();

        heap[size] = value;
        int index = size;
        size++;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[parent] <= heap[index]) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return heap[0];
    }

    public int remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int min = heap[0];

        size--;
        heap[0] = heap[size];

        int index = 0;

        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }

        return min;
    }

    public int[] snapshot() {
        return Arrays.copyOf(heap, size);
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap minHeap = new ArrayMinHeap();

        int[] values = {
            45, 20, 60, 10, 35,
            50, 5, 70, 25, 15,
            40, 30, 55, 65, 75,
            8, 12, 18, 22, 28,
            3, 48, 33, 90
        };

        for (int value : values) {
            minHeap.add(value);
            System.out.println(
                "add " + value + ": "
                + Arrays.toString(minHeap.snapshot())
            );
        }

        System.out.println();
        System.out.println("peek: " + minHeap.peek());
        System.out.println();

        System.out.println("Remove order:");

        int previous = Integer.MIN_VALUE;
        boolean nonDecreasing = true;

        while (minHeap.snapshot().length > 0) {
            int current = minHeap.remove();

            System.out.print(current + " ");

            if (current < previous) {
                nonDecreasing = false;
            }

            previous = current;
        }

        System.out.println();
        System.out.println(
            "non-decreasing: " + nonDecreasing
        );

        try {
            minHeap.peek();
        } catch (NoSuchElementException e) {
            System.out.println(
                "empty peek: NoSuchElementException"
            );
        }

        try {
            minHeap.remove();
        } catch (NoSuchElementException e) {
            System.out.println(
                "empty remove: NoSuchElementException"
            );
        }
    }
}