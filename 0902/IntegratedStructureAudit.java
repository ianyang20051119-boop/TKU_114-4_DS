public class IntegratedStructureAudit {

    static class TestCase {
        private final String scenario;
        private final String structure;
        private final boolean reasonable;
        private final String diagnosis;
        private final String bigO;

        public TestCase(
                String scenario,
                String structure,
                boolean reasonable,
                String diagnosis,
                String bigO) {

            this.scenario = scenario;
            this.structure = structure;
            this.reasonable = reasonable;
            this.diagnosis = diagnosis;
            this.bigO = bigO;
        }

        public void print(int number) {
            System.out.println("Test " + number);
            System.out.println("Scenario: " + scenario);
            System.out.println("Structure: " + structure);
            System.out.println(
                    "Result: " + (reasonable ? "REASONABLE" : "NOT REASONABLE")
            );
            System.out.println("Diagnosis: " + diagnosis);
            System.out.println("Big-O: " + bigO);
            System.out.println();
        }
    }

    public static void main(String[] args) {

        TestCase[] tests = {
            new TestCase(
                "依索引頻繁讀取學生資料",
                "List (ArrayList)",
                true,
                "ArrayList 支援快速的 index 存取，適合此需求。",
                "get: O(1)"
            ),

            new TestCase(
                "每次都要從 List 中搜尋指定 id",
                "List",
                false,
                "List 通常需要逐筆搜尋，若大量依 id 查詢應考慮 Hash Table。",
                "search: O(n)"
            ),

            new TestCase(
                "依照顧客到達順序提供服務",
                "Queue",
                true,
                "Queue 符合 FIFO，可依到達順序處理。",
                "offer/poll: O(1)"
            ),

            new TestCase(
                "需要每次取得最高優先權工作",
                "Queue",
                false,
                "一般 Queue 只依加入順序處理，應使用 Heap 或 PriorityQueue。",
                "尋找最高優先權可能為 O(n)"
            ),

            new TestCase(
                "保存可排序的編號並進行搜尋",
                "BST",
                true,
                "BST 可依 key 維持搜尋結構，平均搜尋效率良好。",
                "average search/insert: O(log n)"
            ),

            new TestCase(
                "只需要依加入順序處理資料",
                "BST",
                false,
                "BST 不適合單純 FIFO 操作，此情況使用 Queue 更直接。",
                "BST operation: average O(log n)"
            ),

            new TestCase(
                "每次取得目前最小值",
                "Heap",
                true,
                "Min Heap 可直接取得最小元素。",
                "peek: O(1), add/remove: O(log n)"
            ),

            new TestCase(
                "需要依 key 直接取得完整資料",
                "Heap",
                false,
                "Heap 適合優先權操作，不適合任意 key 查詢，應考慮 Hash Table。",
                "arbitrary search: O(n)"
            ),

            new TestCase(
                "依學生 id 快速查詢學生資料",
                "Hash Table",
                true,
                "Hash Table 適合 key-value 查詢。",
                "average get/put: O(1)"
            ),

            new TestCase(
                "需要依 key 排序輸出所有資料",
                "Hash Table",
                false,
                "一般 Hash Table 不維持排序順序，需要額外排序或使用其他結構。",
                "traversal: O(n), sorting: O(n log n)"
            ),

            new TestCase(
                "保存校園地點之間的道路連接關係",
                "Graph",
                true,
                "Graph 適合表示 vertex 與 edge 形成的網路關係。",
                "BFS/DFS: O(V + E)"
            ),

            new TestCase(
                "只需要保存一組依序排列的成績",
                "Graph",
                false,
                "資料沒有 vertex-edge 關係，使用 List 會更簡單合理。",
                "Graph unnecessary"
            )
        };

        int reasonableCount = 0;
        int unreasonableCount = 0;

        System.out.println("=== Integrated Structure Audit ===");
        System.out.println();

        for (int i = 0; i < tests.length; i++) {
            tests[i].print(i + 1);

            if (tests[i].reasonable) {
                reasonableCount++;
            } else {
                unreasonableCount++;
            }
        }

        System.out.println("=== Audit Summary ===");
        System.out.println("Total tests: " + tests.length);
        System.out.println("Reasonable: " + reasonableCount);
        System.out.println("Not reasonable: " + unreasonableCount);
    }
}