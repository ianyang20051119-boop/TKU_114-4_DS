import java.util.ArrayList;
import java.util.List;

public class CourseBstIndex {

    static class Course {
        private String courseCode;
        private String name;
        private int credit;

        public Course(String courseCode, String name, int credit) {
            this.courseCode = courseCode;
            this.name = name;
            this.credit = validCredit(credit) ? credit : 1;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getName() {
            return name;
        }

        public int getCredit() {
            return credit;
        }

        public boolean setCredit(int credit) {
            if (!validCredit(credit)) {
                return false;
            }

            this.credit = credit;
            return true;
        }

        private static boolean validCredit(int credit) {
            return credit >= 1 && credit <= 6;
        }

        @Override
        public String toString() {
            return courseCode + " " + name + " credit=" + credit;
        }
    }

    static class Node {
        Course course;
        Node left;
        Node right;

        Node(Course course) {
            this.course = course;
        }
    }

    private Node root;
    private int size;

    public boolean add(Course course) {
        if (course == null || course.getCourseCode() == null) {
            return false;
        }

        if (root == null) {
            root = new Node(course);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int compare = course.getCourseCode()
                    .compareTo(current.course.getCourseCode());

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {
                if (current.left == null) {
                    current.left = new Node(course);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(course);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Course find(String courseCode) {
        if (courseCode == null) {
            return null;
        }

        Node current = root;

        while (current != null) {
            int compare = courseCode.compareTo(
                    current.course.getCourseCode()
            );

            if (compare == 0) {
                return current.course;
            }

            if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateCredit(String courseCode, int credit) {
        Course course = find(courseCode);

        if (course == null) {
            return false;
        }

        return course.setCredit(credit);
    }

    public boolean remove(String courseCode) {
        Course course = find(courseCode);

        if (course == null) {
            return false;
        }

        root = remove(root, courseCode);
        size--;
        return true;
    }

    private Node remove(Node node, String courseCode) {
        if (node == null) {
            return null;
        }

        int compare = courseCode.compareTo(
                node.course.getCourseCode()
        );

        if (compare < 0) {
            node.left = remove(node.left, courseCode);
        } else if (compare > 0) {
            node.right = remove(node.right, courseCode);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.course = successor.course;

            node.right = remove(
                    node.right,
                    successor.course.getCourseCode()
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

    public List<Course> codeRange(
            String lowCode,
            String highCode) {

        List<Course> result = new ArrayList<>();

        if (lowCode == null || highCode == null) {
            return result;
        }

        if (lowCode.compareTo(highCode) > 0) {
            return result;
        }

        codeRange(root, lowCode, highCode, result);
        return result;
    }

    private void codeRange(
            Node node,
            String lowCode,
            String highCode,
            List<Course> result) {

        if (node == null) {
            return;
        }

        String code = node.course.getCourseCode();

        if (code.compareTo(lowCode) > 0) {
            codeRange(
                    node.left,
                    lowCode,
                    highCode,
                    result
            );
        }

        if (code.compareTo(lowCode) >= 0
                && code.compareTo(highCode) <= 0) {
            result.add(node.course);
        }

        if (code.compareTo(highCode) < 0) {
            codeRange(
                    node.right,
                    lowCode,
                    highCode,
                    result
            );
        }
    }

    public List<Course> sortedReport() {
        List<Course> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(
            Node node,
            List<Course> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.course);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        CourseBstIndex index = new CourseBstIndex();

        System.out.println(index.add(
                new Course("CS300", "Data Structures", 3)
        ));

        System.out.println(index.add(
                new Course("CS100", "Programming", 3)
        ));

        System.out.println(index.add(
                new Course("CS500", "Algorithms", 4)
        ));

        System.out.println(index.add(
                new Course("CS200", "Object Oriented Programming", 3)
        ));

        System.out.println(index.add(
                new Course("CS400", "Database Systems", 3)
        ));

        System.out.println(index.add(
                new Course("CS300", "Duplicate Course", 2)
        ));

        System.out.println();

        System.out.println(
                "Find CS200: " + index.find("CS200")
        );

        System.out.println(
                "Find CS999: " + index.find("CS999")
        );

        System.out.println();

        System.out.println(
                "Update CS200 credit: "
                        + index.updateCredit("CS200", 4)
        );

        System.out.println(
                "Invalid credit 8: "
                        + index.updateCredit("CS200", 8)
        );

        System.out.println(
                "CS200: " + index.find("CS200")
        );

        System.out.println();

        System.out.println("Range CS200 ~ CS400:");

        for (Course course :
                index.codeRange("CS200", "CS400")) {
            System.out.println(course);
        }

        System.out.println();

        System.out.println(
                "Remove CS300: "
                        + index.remove("CS300")
        );

        System.out.println(
                "Remove CS999: "
                        + index.remove("CS999")
        );

        System.out.println();

        System.out.println("Sorted Report:");

        for (Course course : index.sortedReport()) {
            System.out.println(course);
        }

        System.out.println();

        System.out.println("Size: " + index.size());
    }
}