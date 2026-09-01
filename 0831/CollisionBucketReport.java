import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    public static void report(int[] keys, int bucketCount) {
        if (bucketCount <= 0) {
            System.out.println("Invalid bucket count");
            return;
        }

        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        if (keys != null) {
            for (int key : keys) {
                int index = Math.floorMod(key, bucketCount);
                buckets.get(index).add(key);
            }
        }

        int collisions = 0;
        int longestChain = 0;

        for (int i = 0; i < bucketCount; i++) {
            List<Integer> bucket = buckets.get(i);

            System.out.println(
                "bucket " + i + ": " + bucket
            );

            if (bucket.size() > 1) {
                collisions += bucket.size() - 1;
            }

            if (bucket.size() > longestChain) {
                longestChain = bucket.size();
            }
        }

        System.out.println("collisions: " + collisions);
        System.out.println("longest chain: " + longestChain);
    }

    public static void main(String[] args) {
        int[] keys = {
            10, 15, 20, 25, -5, 10, -12, 7
        };

        System.out.println("Test 1");
        report(keys, 5);

        System.out.println();

        System.out.println("Test 2 - empty");
        report(new int[0], 5);

        System.out.println();

        System.out.println("Test 3 - null");
        report(null, 5);
    }
}