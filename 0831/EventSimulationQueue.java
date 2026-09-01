import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    static class Event {
        private int time;
        private String type;
        private int sequence;

        public Event(int time, String type, int sequence) {
            this.time = time;
            this.type = type;
            this.sequence = sequence;
        }

        public int getTime() {
            return time;
        }

        public String getType() {
            return type;
        }

        public int getSequence() {
            return sequence;
        }

        @Override
        public String toString() {
            return time + "|" + type + "|" + sequence;
        }
    }

    private final PriorityQueue<Event> queue;

    public EventSimulationQueue() {
        queue = new PriorityQueue<>((a, b) -> {
            if (a.getTime() != b.getTime()) {
                return Integer.compare(a.getTime(), b.getTime());
            }

            return Integer.compare(
                    a.getSequence(),
                    b.getSequence()
            );
        });
    }

    public void addEvent(Event event) {
        if (event != null) {
            queue.offer(event);
        }
    }

    public boolean cancelEvent(int sequence) {
        Event target = null;

        for (Event event : queue) {
            if (event.getSequence() == sequence) {
                target = event;
                break;
            }
        }

        if (target == null) {
            return false;
        }

        return queue.remove(target);
    }

    public List<String> runAll() {
        List<String> log = new ArrayList<>();

        while (!queue.isEmpty()) {
            Event event = queue.poll();
            log.add(event.toString());
        }

        return log;
    }

    public static void main(String[] args) {
        EventSimulationQueue simulation =
                new EventSimulationQueue();

        simulation.addEvent(
                new Event(10, "LOGIN", 1)
        );
        simulation.addEvent(
                new Event(5, "MESSAGE", 2)
        );
        simulation.addEvent(
                new Event(10, "LOGOUT", 3)
        );
        simulation.addEvent(
                new Event(5, "ALERT", 4)
        );
        simulation.addEvent(
                new Event(20, "BACKUP", 5)
        );
        simulation.addEvent(
                new Event(10, "UPDATE", 6)
        );

        System.out.println(
                "cancel sequence 3: "
                        + simulation.cancelEvent(3)
        );

        System.out.println(
                "cancel sequence 99: "
                        + simulation.cancelEvent(99)
        );

        System.out.println("Execution Log:");

        List<String> log = simulation.runAll();

        for (String record : log) {
            System.out.println(record);
        }
    }
}