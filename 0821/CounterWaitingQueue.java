import java.util.ArrayDeque;
import java.util.Deque;

public class CounterWaitingQueue {

    static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private Deque<Customer> queue = new ArrayDeque<>();

    public void addCustomer(Customer customer) {
        if (customer != null) {
            queue.offerLast(customer);
            System.out.println(customer + " 已加入等候隊列");
        }
    }

    public Customer nextCustomer() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.peekFirst();
    }

    public Customer serveNext() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.pollFirst();
    }

    public int waitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        counter.addCustomer(new Customer("王小明"));
        counter.addCustomer(new Customer("陳小華"));
        counter.addCustomer(new Customer("林小美"));

        System.out.println("下一位：" + counter.nextCustomer());
        System.out.println("服務：" + counter.serveNext());
        System.out.println("下一位：" + counter.nextCustomer());
        System.out.println("等候人數：" + counter.waitingCount());

        System.out.println("服務：" + counter.serveNext());
        System.out.println("服務：" + counter.serveNext());

        Customer customer = counter.serveNext();

        if (customer == null) {
            System.out.println("目前沒有顧客等待");
        } else {
            System.out.println("服務：" + customer);
        }

        System.out.println("等候人數：" + counter.waitingCount());
    }
}