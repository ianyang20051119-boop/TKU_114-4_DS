import java.util.ArrayList;
import java.util.List;

public class ScoreRangeBst {

    static class Student {
        private String studentId;
        private String name;
        private int score;

        public Student(String studentId, String name, int score) {
            this.studentId = studentId;
            this.name = name;
            this.score = score;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return studentId + " " + name + " score=" + score;
        }
    }

    static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;
    private int size;

    public boolean add(Student student) {
        if (student == null || student.getStudentId() == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int compare = compareStudent(student, current.student);

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {
                if (current.left == null) {
                    current.left = new Node(student);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    private int compareStudent(Student a, Student b) {
        if (a.getScore() != b.getScore()) {
            return Integer.compare(a.getScore(), b.getScore());
        }

        return a.getStudentId().compareTo(b.getStudentId());
    }

    public int size() {
        return size;
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Student> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.student);
        inorder(node.right, result);
    }

    public List<Student> scoreRange(int low, int high) {
        List<Student> result = new ArrayList<>();

        if (low > high) {
            return result;
        }

        scoreRange(root, low, high, result);
        return result;
    }

    private void scoreRange(
            Node node,
            int low,
            int high,
            List<Student> result) {

        if (node == null) {
            return;
        }

        int score = node.student.getScore();

        if (score >= low) {
            scoreRange(node.left, low, high, result);
        }

        if (score >= low && score <= high) {
            result.add(node.student);
        }

        if (score <= high) {
            scoreRange(node.right, low, high, result);
        }
    }

    public static void main(String[] args) {
        ScoreRangeBst tree = new ScoreRangeBst();

        tree.add(new Student("S005", "Amy", 85));
        tree.add(new Student("S002", "Ben", 70));
        tree.add(new Student("S008", "Cindy", 90));
        tree.add(new Student("S001", "David", 85));
        tree.add(new Student("S006", "Eva", 75));
        tree.add(new Student("S003", "Frank", 90));
        tree.add(new Student("S007", "Grace", 60));
        tree.add(new Student("S004", "Helen", 85));

        System.out.println("Size: " + tree.size());

        System.out.println("Inorder:");
        for (Student student : tree.inorder()) {
            System.out.println(student);
        }

        System.out.println();

        System.out.println("Score Range 75 ~ 90:");
        for (Student student : tree.scoreRange(75, 90)) {
            System.out.println(student);
        }

        System.out.println();

        System.out.println("Score Range 85 ~ 85:");
        for (Student student : tree.scoreRange(85, 85)) {
            System.out.println(student);
        }

        System.out.println();

        System.out.println("Score Range 90 ~ 70:");
        for (Student student : tree.scoreRange(90, 70)) {
            System.out.println(student);
        }
    }
}