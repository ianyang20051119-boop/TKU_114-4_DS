import java.util.*;

public class WildcardNumberTools {

    static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (Number value : values) {
            sum += value.doubleValue();
        }

        return sum / values.size();
    }

    static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }

        double max = values.get(0).doubleValue();

        for (Number value : values) {
            if (value.doubleValue() > max) {
                max = value.doubleValue();
            }
        }

        return max;
    }

    static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(10, 20, 30, 40);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5, 4.5);

        System.out.println("Integer 平均值：" + average(integers));
        System.out.println("Integer 最大值：" + maximum(integers));

        System.out.println("Double 平均值：" + average(doubles));
        System.out.println("Double 最大值：" + maximum(doubles));

        List<Integer> numbers = new ArrayList<>();
        addRange(numbers, 1, 5);

        System.out.println("加入範圍後：" + numbers);

        addRange(numbers, 10, 5);

        System.out.println("start > end：" + numbers);

        List<Number> values = new ArrayList<>();
        addRange(values, 6, 8);

        System.out.println("Number List：" + values);

        List<Integer> empty = new ArrayList<>();

        System.out.println("空 List average：" + average(empty));
        System.out.println("空 List maximum：" + maximum(empty));
    }
}