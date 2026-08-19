import java.util.Objects;

class LibraryMember {

    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "會員編號：" + memberId
                + "，姓名：" + name
                + "，Email：" + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LibraryMember other = (LibraryMember) obj;
        return Objects.equals(memberId, other.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {

    public static void main(String[] args) {

        LibraryMember member1 =
                new LibraryMember("M001", "王小明", "a123@gmail.com");

        LibraryMember member2 =
                new LibraryMember("M001", "王小明", "newmail@gmail.com");

        System.out.println("=== 會員資料 ===");
        System.out.println(member1);
        System.out.println(member2);

        System.out.println("\n=== 比較結果 ===");
        System.out.println("member1 == member2 : " + (member1 == member2));
        System.out.println("member1.equals(member2) : "
                + member1.equals(member2));

        System.out.println("member1.equals(null) : "
                + member1.equals(null));
    }
}