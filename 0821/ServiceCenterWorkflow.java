import java.util.*;

public class ServiceCenterWorkflow {

    static class ServiceTicket {
        private String id;
        private String customer;
        private String service;

        public ServiceTicket(String id, String customer, String service) {
            this.id = id;
            this.customer = customer;
            this.service = service;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " - " + customer + " - " + service;
        }
    }

    private Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private Set<String> ticketIds = new HashSet<>();

    public boolean createTicket(ServiceTicket ticket) {
        if (ticket == null || ticketIds.contains(ticket.getId())) {
            return false;
        }

        ticketMap.put(ticket.getId(), ticket);
        ticketIds.add(ticket.getId());
        waitingQueue.offerLast(ticket);

        return true;
    }

    public ServiceTicket processNext() {
        if (waitingQueue.isEmpty()) {
            return null;
        }

        ServiceTicket ticket = waitingQueue.pollFirst();
        completedStack.push(ticket);

        return ticket;
    }

    public boolean cancelWaiting(String id) {
        ServiceTicket ticket = ticketMap.get(id);

        if (ticket == null) {
            return false;
        }

        if (waitingQueue.remove(ticket)) {
            ticketMap.remove(id);
            ticketIds.remove(id);
            return true;
        }

        return false;
    }

    public ServiceTicket undoLastCompletion() {
        if (completedStack.isEmpty()) {
            return null;
        }

        ServiceTicket ticket = completedStack.pop();
        waitingQueue.offerFirst(ticket);

        return ticket;
    }

    public ServiceTicket findById(String id) {
        return ticketMap.get(id);
    }

    public void printSummary() {
        System.out.println("===== Service Center Summary =====");
        System.out.println("Ticket 數量：" + ticketMap.size());
        System.out.println("等待 Queue：" + waitingQueue);
        System.out.println("完成 Stack：" + completedStack);
        System.out.println();
    }

    public static void main(String[] args) {

        ServiceCenterWorkflow system = new ServiceCenterWorkflow();

        System.out.println("建立 Ticket：");
        System.out.println(system.createTicket(
                new ServiceTicket("T001", "王小明", "開戶")));
        System.out.println(system.createTicket(
                new ServiceTicket("T002", "陳小華", "補辦卡片")));
        System.out.println(system.createTicket(
                new ServiceTicket("T003", "林小美", "修改資料")));

        System.out.println("\n測試重複 id：");
        System.out.println(system.createTicket(
                new ServiceTicket("T002", "張小強", "其他服務")));

        system.printSummary();

        System.out.println("處理第一筆：");
        System.out.println(system.processNext());

        System.out.println("\n取消尚未處理 T003：");
        System.out.println(system.cancelWaiting("T003"));

        System.out.println("\n取消不存在 T999：");
        System.out.println(system.cancelWaiting("T999"));

        System.out.println("\n查詢 T001：");
        System.out.println(system.findById("T001"));

        System.out.println();
        system.printSummary();

        System.out.println("處理第二筆：");
        System.out.println(system.processNext());

        System.out.println("\n連續第一次 undo：");
        System.out.println(system.undoLastCompletion());

        System.out.println("\n連續第二次 undo：");
        System.out.println(system.undoLastCompletion());

        System.out.println("\n再次 undo，測試空 Stack：");
        System.out.println(system.undoLastCompletion());

        system.printSummary();

        System.out.println("清空等待 Queue：");
        while (system.processNext() != null) {
            System.out.println("處理完成");
        }

        System.out.println("\n空 Queue 測試：");
        System.out.println(system.processNext());

        system.printSummary();
    }
}