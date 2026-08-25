public class LinkedTaskListSystem {

    static class Task {
        private String id;
        private String name;

        public Task(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " - " + name;
        }
    }

    static class TaskNode {
        Task task;
        TaskNode next;

        public TaskNode(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    static class TaskLinkedList {
        private TaskNode head;
        private int size;

        public boolean addFirst(Task task) {
            if (task == null || findById(task.getId()) != null) {
                return false;
            }

            TaskNode newNode = new TaskNode(task);
            newNode.next = head;
            head = newNode;
            size++;

            return true;
        }

        public boolean addLast(Task task) {
            if (task == null || findById(task.getId()) != null) {
                return false;
            }

            TaskNode newNode = new TaskNode(task);

            if (head == null) {
                head = newNode;
                size++;
                return true;
            }

            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
            size++;

            return true;
        }

        public Task findById(String id) {
            TaskNode current = head;

            while (current != null) {
                if (current.task.getId().equals(id)) {
                    return current.task;
                }

                current = current.next;
            }

            return null;
        }

        public boolean removeById(String id) {
            if (head == null) {
                return false;
            }

            if (head.task.getId().equals(id)) {
                head = head.next;
                size--;
                return true;
            }

            TaskNode current = head;

            while (current.next != null) {
                if (current.next.task.getId().equals(id)) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        public boolean insertAfter(String existingId, Task task) {
            if (task == null || findById(task.getId()) != null) {
                return false;
            }

            TaskNode current = head;

            while (current != null) {
                if (current.task.getId().equals(existingId)) {
                    TaskNode newNode = new TaskNode(task);
                    newNode.next = current.next;
                    current.next = newNode;
                    size++;
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        public int size() {
            return size;
        }

        public void printAll() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }

            TaskNode current = head;

            while (current != null) {
                System.out.println(current.task);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {

        TaskLinkedList list = new TaskLinkedList();

        System.out.println("空 list：");
        list.printAll();
        System.out.println("size = " + list.size());

        list.addLast(new Task("T001", "寫報告"));
        list.addLast(new Task("T002", "完成作業"));
        list.addLast(new Task("T003", "準備考試"));
        list.addLast(new Task("T004", "複習程式"));

        System.out.println("\n初始資料：");
        list.printAll();

        System.out.println("\n重複 id T002："
                + list.addLast(new Task("T002", "其他工作")));

        System.out.println("\naddFirst：");
        list.addFirst(new Task("T000", "開始工作"));
        list.printAll();

        System.out.println("\ninsertAfter T002：");
        list.insertAfter("T002", new Task("T002A", "補充作業"));
        list.printAll();

        System.out.println("\nfindById T003：");
        System.out.println(list.findById("T003"));

        System.out.println("\n刪除 head T000：");
        System.out.println(list.removeById("T000"));
        list.printAll();

        System.out.println("\n刪除 middle T002：");
        System.out.println(list.removeById("T002"));
        list.printAll();

        System.out.println("\n刪除 tail T004：");
        System.out.println(list.removeById("T004"));
        list.printAll();

        System.out.println("\n找不到 id T999：");
        System.out.println(list.findById("T999"));

        System.out.println("\n刪除不存在的 T999：");
        System.out.println(list.removeById("T999"));

        System.out.println("\n最後 size = " + list.size());
    }
}