import java.util.Objects;

public class GenericArrayTools {

    static <T> int countMatches(T[] data, T target) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int count = 0;

        for (T item : data) {
            if (Objects.equals(item, target)) {
                count++;
            }
        }

        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null || data.length == 0) {
            return;
        }

        if (first < 0 || first >= data.length ||
            second < 0 || second >= data.length) {
            return;
        }

        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] names = {"Amy", "Bob", "Amy", null, "David"};

        System.out.println("Amy 出現次數：" + countMatches(names, "Amy"));
        System.out.println("null 出現次數：" + countMatches(names, null));
        System.out.println("最後一個元素：" + last(names));

        swap(names, 0, 1);

        for (String name : names) {
            System.out.print(name + " ");
        }

        System.out.println();

        Integer[] numbers = {10, 20, 30, 20, 40};

        System.out.println("20 出現次數：" + countMatches(numbers, 20));
        System.out.println("最後一個元素：" + last(numbers));

        swap(numbers, 0, 4);

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        String[] nullArray = null;
        Integer[] emptyArray = {};

        System.out.println(countMatches(nullArray, "Amy"));
        System.out.println(last(nullArray));
        System.out.println(last(emptyArray));

        swap(numbers, -1, 10);
    }
}