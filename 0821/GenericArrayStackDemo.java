public class GenericArrayStackDemo {

    static class ArrayStack<T> {

        private Object[] data;
        private int top;

        public ArrayStack(int capacity) {
            data = new Object[capacity];
            top = -1;
        }

        public boolean push(T value) {
            if (isFull()) {
                return false;
            }

            data[++top] = value;
            return true;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) {
                return null;
            }

            T value = (T) data[top];
            data[top--] = null;
            return value;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                return null;
            }

            return (T) data[top];
        }

        public int size() {
            return top + 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == data.length - 1;
        }
    }

    public static void main(String[] args) {

        ArrayStack<String> stringStack = new ArrayStack<>(3);

        stringStack.push("Apple");
        stringStack.push("Banana");
        stringStack.push("Orange");

        System.out.println("String Stack");
        System.out.println("目前頂端：" + stringStack.peek());
        System.out.println("取出：" + stringStack.pop());
        System.out.println("目前大小：" + stringStack.size());
        System.out.println("是否為空：" + stringStack.isEmpty());
        System.out.println("是否已滿：" + stringStack.isFull());

        System.out.println();

        ArrayStack<Integer> integerStack = new ArrayStack<>(3);

        integerStack.push(10);
        integerStack.push(20);
        integerStack.push(30);

        System.out.println("Integer Stack");
        System.out.println("目前頂端：" + integerStack.peek());
        System.out.println("取出：" + integerStack.pop());
        System.out.println("目前大小：" + integerStack.size());
        System.out.println("是否為空：" + integerStack.isEmpty());
        System.out.println("是否已滿：" + integerStack.isFull());
    }
}