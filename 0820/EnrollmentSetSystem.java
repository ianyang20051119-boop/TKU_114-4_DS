import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private String studentId;
    private String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Enrollment)) {
            return false;
        }

        Enrollment other = (Enrollment) obj;

        return Objects.equals(studentId, other.studentId)
                && Objects.equals(courseCode, other.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return studentId + " - " + courseCode;
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        Enrollment e1 = new Enrollment("S001", "CS101");
        Enrollment e2 = new Enrollment("S001", "CS102");
        Enrollment e3 = new Enrollment("S001", "CS101");

        System.out.println("加入 S001 + CS101：" + enrollments.add(e1));
        System.out.println("加入 S001 + CS102：" + enrollments.add(e2));
        System.out.println("重複加入 S001 + CS101：" + enrollments.add(e3));

        System.out.println();

        Enrollment sameEnrollment = new Enrollment("S001", "CS101");

        System.out.println("contains 相同身分 object：" +
                enrollments.contains(sameEnrollment));

        System.out.println("remove 相同身分 object：" +
                enrollments.remove(sameEnrollment));

        System.out.println();

        Enrollment e4 = new Enrollment("S002", "CS101");

        System.out.println("加入 S002 + CS101：" + enrollments.add(e4));

        System.out.println();

        System.out.println("目前選課：");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }
}