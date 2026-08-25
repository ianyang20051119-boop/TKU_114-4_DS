import java.util.*;

public class CollectionChoiceReport {

    public static void main(String[] args) {

        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java");
        searchHistory.add("Java");
        searchHistory.add("ArrayList");

        System.out.println("1. 保留搜尋紀錄且允許重複");
        System.out.println("Interface: List");
        System.out.println("Implementation: ArrayList");
        System.out.println("操作結果：" + searchHistory);

        Set<String> memberIds = new HashSet<>();
        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001");

        System.out.println();
        System.out.println("2. 保存不重複會員編號");
        System.out.println("Interface: Set");
        System.out.println("Implementation: HashSet");
        System.out.println("操作結果：" + memberIds);

        Map<String, Integer> scores = new HashMap<>();
        scores.put("S001", 85);
        scores.put("S002", 92);
        scores.put("S003", 78);

        System.out.println();
        System.out.println("3. 以學號查詢成績");
        System.out.println("Interface: Map");
        System.out.println("Implementation: HashMap");
        System.out.println("操作結果：S002 成績 = " + scores.get("S002"));

        Queue<String> printQueue = new LinkedList<>();
        printQueue.offer("文件A");
        printQueue.offer("文件B");
        printQueue.offer("文件C");

        System.out.println();
        System.out.println("4. 依到達順序處理列印工作");
        System.out.println("Interface: Queue");
        System.out.println("Implementation: LinkedList");
        System.out.println("操作結果：");

        while (!printQueue.isEmpty()) {
            System.out.println("處理：" + printQueue.poll());
        }

        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("輸入文字");
        undoStack.push("刪除文字");
        undoStack.push("修改文字");

        System.out.println();
        System.out.println("5. 復原最近操作");
        System.out.println("Interface: Deque");
        System.out.println("Implementation: ArrayDeque");
        System.out.println("操作結果：復原 " + undoStack.pop());
    }
}