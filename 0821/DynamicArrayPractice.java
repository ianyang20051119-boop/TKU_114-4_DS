public class DynamicArrayPractice {

    static class DynamicArray<T> {

        private Object[] data;
        private int size;

        public DynamicArray() {
            data = new Object[4];
            size = 0;
        }

        public void add(T value) {
            ensureCapacity();
            data[size++] = value;
        }

        public void add(int index, T value) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }

            ensureCapacity();

            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }

            data[index] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            checkIndex(index);
            return (T) data[index];
        }

        @SuppressWarnings("unchecked")
        public T set(int index, T value) {
            checkIndex(index);

            T oldValue = (T) data[index];
            data[index] = value;

            return oldValue;
        }

        @SuppressWarnings("unchecked")
        public T remove(int index) {
            checkIndex(index);

            T removedValue = (T) data[index];

            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }

            data[size - 1] = null;
            size--;

            return removedValue;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return data.length;
        }

        private void ensureCapacity() {
            if (size == data.length) {
                Object[] newData = new Object[data.length * 2];

                for (int i = 0; i < data.length; i++) {
                    newData[i] = data[i];
                }

                data = newData;
            }
        }

        private void checkIndex(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("[ ");

            for (int i = 0; i < size; i++) {
                result.append(data[i]);

                if (i < size - 1) {
                    result.append(", ");
                }
            }

            result.append(" ]");
            return result.toString();
        }
    }

    public static void main(String[] args) {

        DynamicArray<String> strings = new DynamicArray<>();

        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");

        System.out.println("String:");
        System.out.println(strings);
        System.out.println("size = " + strings.size());
        System.out.println("capacity = " + strings.capacity());

        strings.add("E");
        System.out.println("擴充後：" + strings);
        System.out.println("capacity = " + strings.capacity());

        strings.add(2, "X");
        System.out.println("插入後：" + strings);

        System.out.println("get(2) = " + strings.get(2));
        System.out.println("set(2, Y) 舊值 = " + strings.set(2, "Y"));
        System.out.println("set 後：" + strings);

        System.out.println("remove(1) = " + strings.remove(1));
        System.out.println("刪除後：" + strings);

        try {
            strings.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get(-1)：Index 錯誤");
        }

        try {
            strings.get(strings.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get(size)：Index 錯誤");
        }

        DynamicArray<Integer> numbers = new DynamicArray<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println();
        System.out.println("Integer:");
        System.out.println(numbers);
        System.out.println("size = " + numbers.size());
        System.out.println("capacity = " + numbers.capacity());

        numbers.add(1, 15);
        System.out.println("插入後：" + numbers);

        System.out.println("get(1) = " + numbers.get(1));
        System.out.println("set(1, 25) 舊值 = " + numbers.set(1, 25));
        System.out.println("set 後：" + numbers);

        System.out.println("remove(0) = " + numbers.remove(0));
        System.out.println("刪除後：" + numbers);

        DynamicArray<Integer> empty = new DynamicArray<>();

        try {
            empty.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("空結構刪除：Index 錯誤");
        }
    }
}