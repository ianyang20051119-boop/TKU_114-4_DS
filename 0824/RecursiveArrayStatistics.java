public class RecursiveArrayStatistics {

    public static int maximum(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        return maximum(array, 0);
    }

    private static int maximum(int[] array, int index) {
        if (index == array.length - 1) {
            return array[index];
        }

        int restMax = maximum(array, index + 1);
        return Math.max(array[index], restMax);
    }

    public static int minimum(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        return minimum(array, 0);
    }

    private static int minimum(int[] array, int index) {
        if (index == array.length - 1) {
            return array[index];
        }

        int restMin = minimum(array, index + 1);
        return Math.min(array[index], restMin);
    }

    public static int countAbove(int[] array, int threshold) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        return countAbove(array, threshold, 0);
    }

    private static int countAbove(int[] array, int threshold, int index) {
        if (index == array.length) {
            return 0;
        }

        int count = array[index] > threshold ? 1 : 0;
        return count + countAbove(array, threshold, index + 1);
    }

    public static void main(String[] args) {

        int[] numbers = {10, 25, 7, 42, 18};

        System.out.println("Maximum: " + maximum(numbers));
        System.out.println("Minimum: " + minimum(numbers));
        System.out.println("Count above 20: " + countAbove(numbers, 20));

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("null array：IllegalArgumentException");
        }

        try {
            minimum(new int[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("empty array：IllegalArgumentException");
        }
    }
}