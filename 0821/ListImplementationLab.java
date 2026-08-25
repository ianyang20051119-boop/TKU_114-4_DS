import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    public static void addToEnd(List<Integer> list, int value) {
        list.add(value);
    }

    public static void insertAt(List<Integer> list, int index, int value) {
        if (index >= 0 && index <= list.size()) {
            list.add(index, value);
        } else {
            System.out.println("插入位置不合法：" + index);
        }
    }

    public static int search(List<Integer> list, int target) {
        return list.indexOf(target);
    }

    public static boolean removeValue(List<Integer> list, int value) {
        return list.remove(Integer.valueOf(value));
    }

    public static int sum(List<Integer> list) {
        int total = 0;

        for (int value : list) {
            total += value;
        }

        return total;
    }

    public static void testList(String name, List<Integer> list) {
        System.out.println("========== " + name + " ==========");

        addToEnd(list, 10);
        addToEnd(list, 20);
        addToEnd(list, 30);
        System.out.println("尾端新增後：" + list);

        insertAt(list, 1, 15);
        System.out.println("指定位置插入後：" + list);

        int index = search(list, 20);
        System.out.println("搜尋 20 的結果，索引：" + index);

        boolean removed = removeValue(list, 15);
        System.out.println("刪除 15 是否成功：" + removed);
        System.out.println("刪除後：" + list);

        System.out.println("串列總和：" + sum(list));
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        testList("ArrayList", arrayList);
        testList("LinkedList", linkedList);

        System.out.println("ArrayList 使用動態陣列，依索引存取速度較快，中間插入或刪除可能需要搬移元素。");
        System.out.println("LinkedList 使用節點連結，依索引搜尋需要逐步走訪，插入或刪除找到位置後較方便。");
    }
}