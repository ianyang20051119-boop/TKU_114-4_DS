public class StudentIdHashAnalysis {

    static class AnalysisResult {
        int bucketCount;
        int[] bucketSizes;
        int collisions;
        int maxChain;
        double averageChain;

        AnalysisResult(int bucketCount) {
            this.bucketCount = bucketCount;
            this.bucketSizes = new int[bucketCount];
        }
    }

    public static AnalysisResult analyze(
            int[] studentIds, int bucketCount) {

        if (bucketCount <= 0) {
            throw new IllegalArgumentException();
        }

        AnalysisResult result =
                new AnalysisResult(bucketCount);

        if (studentIds != null) {
            for (int id : studentIds) {
                int index = Math.floorMod(id, bucketCount);
                result.bucketSizes[index]++;
            }
        }

        int total = 0;

        for (int size : result.bucketSizes) {
            total += size;

            if (size > 1) {
                result.collisions += size - 1;
            }

            if (size > result.maxChain) {
                result.maxChain = size;
            }
        }

        result.averageChain =
                (double) total / bucketCount;

        return result;
    }

    public static void printResult(AnalysisResult result) {
        System.out.println(
                "Bucket count: " + result.bucketCount
        );

        for (int i = 0; i < result.bucketSizes.length; i++) {
            System.out.println(
                    "bucket " + i + ": "
                    + result.bucketSizes[i]
            );
        }

        System.out.println(
                "collisions: " + result.collisions
        );

        System.out.println(
                "max chain: " + result.maxChain
        );

        System.out.printf(
                "average chain: %.2f%n",
                result.averageChain
        );
    }

    public static void compare(
            AnalysisResult a, AnalysisResult b) {

        System.out.println("Comparison:");

        System.out.println(
                "collisions: "
                + a.bucketCount + " buckets = "
                + a.collisions + ", "
                + b.bucketCount + " buckets = "
                + b.collisions
        );

        System.out.println(
                "max chain: "
                + a.bucketCount + " buckets = "
                + a.maxChain + ", "
                + b.bucketCount + " buckets = "
                + b.maxChain
        );

        System.out.printf(
                "average chain: %d buckets = %.2f, "
                + "%d buckets = %.2f%n",
                a.bucketCount,
                a.averageChain,
                b.bucketCount,
                b.averageChain
        );
    }

    public static void main(String[] args) {

        int[] studentIds = {
                1001, 1002, 1003, 1004,
                1010, 1015, 1020, 1025,
                1030, 1035, 1040, 1045,
                1050, 1055, 1060
        };

        int bucketCount1 = 5;
        int bucketCount2 = 11;

        AnalysisResult result1 =
                analyze(studentIds, bucketCount1);

        AnalysisResult result2 =
                analyze(studentIds, bucketCount2);

        System.out.println("=== Analysis 1 ===");
        printResult(result1);

        System.out.println();

        System.out.println("=== Analysis 2 ===");
        printResult(result2);

        System.out.println();

        compare(result1, result2);
    }
}