
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {

    static class Request {
        private final String id;
        private final String customer;
        private final int priority;
        private final int sequence;

        public Request(String id, String customer, int priority, int sequence) {
            this.id = id;
            this.customer = customer;
            this.priority = priority;
            this.sequence = sequence;
        }

        public String getId() {
            return id;
        }

        public int getPriority() {
            return priority;
        }

        public int getSequence() {
            return sequence;
        }

        @Override
        public String toString() {
            return id + " | " + customer +
                   " | priority=" + priority +
                   " | seq=" + sequence;
        }
    }

    private final Map<String, Request> requestMap = new HashMap<>();

    private final PriorityQueue<Request> queue =
            new PriorityQueue<>((a, b) -> {
                if (a.getPriority() != b.getPriority()) {
                    return Integer.compare(b.getPriority(), a.getPriority());
                }
                return Integer.compare(a.getSequence(), b.getSequence());
            });

    public boolean addRequest(Request request) {
        if (request == null || requestMap.containsKey(request.getId())) {
            return false;
        }

        requestMap.put(request.getId(), request);
        queue.offer(request);
        return true;
    }

    public Request findRequest(String id) {
        return requestMap.get(id);
    }

    public Request nextRequest() {
        while (!queue.isEmpty()) {
            Request request = queue.poll();

            if (requestMap.containsKey(request.getId())) {
                requestMap.remove(request.getId());
                return request;
            }
        }
        return null;
    }

    public boolean cancelRequest(String id) {
        Request request = requestMap.remove(id);

        if (request == null) {
            return false;
        }

        queue.remove(request);
        return true;
    }

    public int size() {
        return requestMap.size();
    }

    public static void main(String[] args) {

        ServiceRequestSystem system = new ServiceRequestSystem();

        system.addRequest(new Request("R001", "Amy", 2, 1));
        system.addRequest(new Request("R002", "Ben", 5, 2));
        system.addRequest(new Request("R003", "Cara", 4, 3));
        system.addRequest(new Request("R004", "David", 5, 4));
        system.addRequest(new Request("R005", "Eva", 1, 5));

        System.out.println("Find R003:");
        System.out.println(system.findRequest("R003"));

        System.out.println();

        System.out.println("Cancel R003: " +
                system.cancelRequest("R003"));

        System.out.println("Find R003:");
        System.out.println(system.findRequest("R003"));

        System.out.println();

        System.out.println("Processing Requests:");
        Request request;

        while ((request = system.nextRequest()) != null) {
            System.out.println("Serve -> " + request);
        }

        System.out.println();

        System.out.println("Remaining size: " + system.size());

        System.out.println("Next request: " + system.nextRequest());
    }
}