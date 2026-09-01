public class DataStructureDecisionReport {

    static class Decision {
        private final String requirement;
        private final String choice;
        private final String reason;
        private final String bigO;

        public Decision(
                String requirement,
                String choice,
                String reason,
                String bigO) {

            this.requirement = requirement;
            this.choice = choice;
            this.reason = reason;
            this.bigO = bigO;
        }

        public void print(int number) {
            System.out.println("Requirement " + number);
            System.out.println("Need: " + requirement);
            System.out.println("Choice: " + choice);
            System.out.println("Reason: " + reason);
            System.out.println("Big-O: " + bigO);
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Decision[] decisions = {
            new Decision(
                "依索引快速讀取資料",
                "ArrayList",
                "可直接使用 index 存取元素",
                "get: O(1)"
            ),

            new Decision(
                "頻繁在串列前端新增與刪除",
                "LinkedList",
                "已取得端點時可快速新增與刪除",
                "addFirst/removeFirst: O(1)"
            ),

            new Decision(
                "先加入的工作要先處理",
                "Queue",
                "符合 FIFO 先進先出",
                "offer/poll: O(1)"
            ),

            new Decision(
                "需要 Undo 最近一次操作",
                "Stack",
                "符合 LIFO 後進先出",
                "push/pop: O(1)"
            ),

            new Decision(
                "快速依 key 查詢資料",
                "HashMap",
                "使用 hash 方式依 key 儲存與查詢",
                "put/get: average O(1)"
            ),

            new Decision(
                "資料不可重複且要快速判斷是否存在",
                "HashSet",
                "自動避免重複並支援快速查詢",
                "add/contains: average O(1)"
            ),

            new Decision(
                "每次取得最高優先權工作",
                "PriorityQueue",
                "Heap 可快速取得最高或最低優先權元素",
                "peek: O(1), offer/poll: O(log n)"
            ),

            new Decision(
                "依排序後資料進行快速搜尋",
                "Binary Search",
                "每次搜尋可排除一半資料",
                "search: O(log n)"
            ),

            new Decision(
                "大量資料需要穩定且有效率排序",
                "Merge Sort",
                "時間複雜度穩定且可保持相同值原順序",
                "sort: O(n log n)"
            ),

            new Decision(
                "表示固定頂點數且需要快速判斷兩點是否相連",
                "Adjacency Matrix",
                "可直接使用兩個 vertex index 查詢 edge",
                "edge lookup: O(1), space: O(V^2)"
            ),

            new Decision(
                "表示大型且 edge 較少的 Graph",
                "Adjacency List",
                "只保存實際存在的 edge，較節省空間",
                "space: O(V + E)"
            ),

            new Decision(
                "找出無權重 Graph 的最少 edge 路徑",
                "BFS",
                "逐層搜尋可找到最少 edge 數的路徑",
                "O(V + E)"
            )
        };

        System.out.println("=== Data Structure Decision Report ===");
        System.out.println();

        for (int i = 0; i < decisions.length; i++) {
            decisions[i].print(i + 1);
        }
    }
}