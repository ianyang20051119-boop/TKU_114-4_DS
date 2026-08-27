import java.util.ArrayList;
import java.util.List;

public class StudentBstIndex {

    static class Student {
        private int studentId;
        private String name;
        private int score;

        public Student(int studentId, String name, int score) {
            this.studentId = studentId;
            this.name = name;
            this.score = score;
        }

        public int getStudentId() {
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
            return studentId + " " + name + " " + score;
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

    public boolean insert(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            if (student.getStudentId() == current.student.getStudentId()) {
                return false;
            }

            if (student.getStudentId() < current.student.getStudentId()) {
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

    public Student search(int studentId) {
        Node current = root;

        while (current != null) {
            if (studentId == current.student.getStudentId()) {
                return current.student;
            }

            if (studentId < current.student.getStudentId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean delete(int studentId) {
        if (search(studentId) == null) {
            return false;
        }

        root = delete(root, studentId);
        size--;
        return true;
    }

    private Node delete(Node node, int studentId) {
        if (studentId < node.student.getStudentId()) {
            node.left = delete(node.left, studentId);
        } else if (studentId > node.student.getStudentId()) {
            node.right = delete(node.right, studentId);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.student = successor.student;
            node.right = delete(
                    node.right,
                    successor.student.getStudentId()
            );
        }

        return node;
    }

    private Node findMin(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
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

    public static void main(String[] args) {
        StudentBstIndex index = new StudentBstIndex();

        System.out.println(index.insert(
                new Student(300, "Amy", 85)
        ));

        System.out.println(index.insert(
                new Student(100, "Ben", 78)
        ));

        System.out.println(index.insert(
                new Student(500, "Cindy", 92)
        ));

        System.out.println(index.insert(
                new Student(200, "David", 88)
        ));

        System.out.println(index.insert(
                new Student(400, "Eva", 90)
        ));

        System.out.println(index.insert(
                new Student(300, "Frank", 70)
        ));

        System.out.println("Size: " + index.size());
        System.out.println("Inorder: " + index.inorder());

        System.out.println("Search 200: " + index.search(200));
        System.out.println("Search 999: " + index.search(999));

        System.out.println("Delete 300: " + index.delete(300));
        System.out.println("Delete 999: " + index.delete(999));

        System.out.println("Size: " + index.size());
        System.out.println("Inorder: " + index.inorder());
    }
}