public class RecursiveCallReport {

    public static int sum(int[] data, int index) {
        if (data == null || index >= data.length) {
            System.out.println(
                    "index=" + index
                    + ", current=END"
                    + ", recursive result=0"
                    + ", return value=0"
            );
            return 0;
        }

        int current = data[index];

        int recursiveResult = sum(data, index + 1);

        int returnValue = current + recursiveResult;

        System.out.println(
                "index=" + index
                + ", current value=" + current
                + ", recursive result=" + recursiveResult
                + ", return value=" + returnValue
        );

        return returnValue;
    }

    public static void main(String[] args) {

        int[] normal = {10, 20, 30, 40};

        System.out.println("=== Normal Array ===");
        int result1 = sum(normal, 0);
        System.out.println("Final sum = " + result1);

        System.out.println();

        int[] single = {50};

        System.out.println("=== Single Element ===");
        int result2 = sum(single, 0);
        System.out.println("Final sum = " + result2);

        System.out.println();

        int[] empty = {};

        System.out.println("=== Empty Array ===");
        int result3 = sum(empty, 0);
        System.out.println("Final sum = " + result3);
    }
}