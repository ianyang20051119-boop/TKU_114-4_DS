public class CircularQueuePractice {

    static class CircularQueue<T> {

        private Object[] data;
        private int front;
        private int rear;
        private int size;

        public CircularQueue(int capacity) {
            data = new Object[capacity];
            front = 0;
            rear = 0;
            size = 0;
        }

        public boolean enqueue(T value) {
            if (size == data.length) {
                return false;
            }

            data[rear] = value;
            rear = (rear + 1) % data.length;
            size++;

            printState("enqueue " + value);
            return true;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (size == 0) {
                return null;
            }

            T value = (T) data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;

            printState("dequeue");
            return value;
        }

        private void printState(String operation) {
            System.out.print(operation + " -> array: [");

            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i]);

                if (i < data.length - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println("], front=" + front
                    + ", rear=" + rear
                    + ", size=" + size);
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static void main(String[] args) {

        CircularQueue<String> queue = new CircularQueue<>(4);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        queue.dequeue();
        queue.dequeue();

        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");

        queue.dequeue();
        queue.enqueue("G");

        System.out.println();
        System.out.println("FIFO 取出結果：");

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}