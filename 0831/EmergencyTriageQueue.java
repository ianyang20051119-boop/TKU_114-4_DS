import java.util.Comparator;
import java.util.PriorityQueue;

public class EmergencyTriageQueue {

    static class Patient {
        private String medicalId;
        private int severity;
        private int arrivalOrder;

        public Patient(String medicalId, int severity, int arrivalOrder) {
            this.medicalId = medicalId;
            this.severity = severity;
            this.arrivalOrder = arrivalOrder;
        }

        public String getMedicalId() {
            return medicalId;
        }

        public int getSeverity() {
            return severity;
        }

        public int getArrivalOrder() {
            return arrivalOrder;
        }

        @Override
        public String toString() {
            return medicalId + "|" + severity + "|" + arrivalOrder;
        }
    }

    private final PriorityQueue<Patient> queue;

    public EmergencyTriageQueue() {
        queue = new PriorityQueue<>(new Comparator<Patient>() {
            @Override
            public int compare(Patient a, Patient b) {
                if (a.getSeverity() != b.getSeverity()) {
                    return Integer.compare(
                            b.getSeverity(),
                            a.getSeverity()
                    );
                }

                if (a.getArrivalOrder() != b.getArrivalOrder()) {
                    return Integer.compare(
                            a.getArrivalOrder(),
                            b.getArrivalOrder()
                    );
                }

                return a.getMedicalId().compareTo(b.getMedicalId());
            }
        });
    }

    public void checkIn(Patient patient) {
        if (patient != null) {
            queue.offer(patient);
        }
    }

    public Patient peekNext() {
        return queue.peek();
    }

    public Patient callNext() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage =
                new EmergencyTriageQueue();

        triage.checkIn(new Patient("M105", 3, 1));
        triage.checkIn(new Patient("M203", 5, 2));
        triage.checkIn(new Patient("M101", 5, 3));
        triage.checkIn(new Patient("M305", 2, 4));
        triage.checkIn(new Patient("M150", 5, 5));
        triage.checkIn(new Patient("M120", 3, 6));

        System.out.println("目前人數: " + triage.size());
        System.out.println("下一位: " + triage.peekNext());

        while (triage.size() > 0) {
            Patient patient = triage.callNext();
            System.out.println("叫號: " + patient);
        }

        System.out.println("目前人數: " + triage.size());

        Patient next = triage.peekNext();

        if (next == null) {
            System.out.println("查看下一位: EMPTY");
        }

        Patient called = triage.callNext();

        if (called == null) {
            System.out.println("叫號: EMPTY");
        }
    }
}