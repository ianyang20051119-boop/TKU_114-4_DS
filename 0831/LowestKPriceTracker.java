import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {

    public static List<Integer> lowestK(List<Integer> prices, int k) {
        List<Integer> result = new ArrayList<>();

        if (prices == null || k <= 0) {
            return result;
        }

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) {
                continue;
            }

            if (maxHeap.size() < k) {
                maxHeap.offer(price);
            } else if (price < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(price);
            }
        }

        result.addAll(maxHeap);
        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {
        List<Integer> prices = new ArrayList<>();

        prices.add(120);
        prices.add(50);
        prices.add(null);
        prices.add(80);
        prices.add(-10);
        prices.add(30);
        prices.add(50);
        prices.add(200);
        prices.add(20);

        int k = 4;

        System.out.println("prices: " + prices);
        System.out.println("K: " + k);
        System.out.println("lowest K: " + lowestK(prices, k));

        System.out.println("K = 0: " + lowestK(prices, 0));
        System.out.println("K = -1: " + lowestK(prices, -1));
    }
}