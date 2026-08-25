import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ClinicQueueSystem {

    static class Patient {
        private String id;
        private String name;

        public Patient(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return id + "-" + name;
        }
    }

    private Queue<Patient> waitingQueue = new LinkedList<>();
    private List<Patient> completedList = new ArrayList<>();

    public void register(Patient patient) {
        if (patient != null) {
            waitingQueue.offer(patient);
            System.out.println("掛號：" + patient);
        }
    }

    public boolean cancel(String patientId) {
        for (Patient patient : waitingQueue) {
            if (patient.getId().equals(patientId)) {
                waitingQueue.remove(patient);
                System.out.println("取消：" + patient);
                return true;
            }
        }

        System.out.println("找不到病歷號：" + patientId);
        return false;
    }

    public Patient next() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的病患");
            return null;
        }

        Patient patient = waitingQueue.peek();
        System.out.println("下一位：" + patient);
        return patient;
    }

    public Patient callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有病患可以叫號");
            return null;
        }

        Patient patient = waitingQueue.poll();
        completedList.add(patient);

        System.out.println("叫號：" + patient);
        return patient;
    }

    public void showWaitingCount() {
        System.out.println("目前等待人數：" + waitingQueue.size());
    }

    public void showCompleted() {
        System.out.println("當日完成清單：" + completedList);
    }

    public static void main(String[] args) {

        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.register(new Patient("P001", "王小明"));
        clinic.register(new Patient("P002", "陳小華"));
        clinic.register(new Patient("P003", "林小美"));
        clinic.register(new Patient("P004", "張小強"));

        clinic.showWaitingCount();
        clinic.next();

        clinic.callNext();
        clinic.callNext();

        clinic.cancel("P004");

        clinic.next();
        clinic.showWaitingCount();

        clinic.callNext();
        clinic.callNext();

        clinic.callNext();

        clinic.showCompleted();
        clinic.showWaitingCount();
    }
}