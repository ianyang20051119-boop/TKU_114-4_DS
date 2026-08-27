import java.util.ArrayList;
import java.util.List;

public class MemberBstIndex {

    static class Member {
        private String memberId;
        private String name;
        private String email;

        public Member(String memberId, String name, String email) {
            if (memberId == null || memberId.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.memberId = memberId.trim();
            this.name = name == null ? "" : name.trim();
            this.email = email.trim();
        }

        public String getMemberId() {
            return memberId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public boolean setEmail(String email) {
            if (email == null || email.trim().isEmpty()) {
                return false;
            }

            this.email = email.trim();
            return true;
        }

        @Override
        public String toString() {
            return memberId + " " + name + " " + email;
        }
    }

    static class Node {
        Member member;
        Node left;
        Node right;

        Node(Member member) {
            this.member = member;
        }
    }

    private Node root;
    private int size;

    public boolean add(Member member) {
        if (member == null) {
            return false;
        }

        if (root == null) {
            root = new Node(member);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int compare = member.getMemberId()
                    .compareTo(current.member.getMemberId());

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {
                if (current.left == null) {
                    current.left = new Node(member);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(member);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Member find(String memberId) {
        if (memberId == null) {
            return null;
        }

        Node current = root;
        String id = memberId.trim();

        while (current != null) {
            int compare = id.compareTo(
                    current.member.getMemberId()
            );

            if (compare == 0) {
                return current.member;
            }

            if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateEmail(String memberId, String email) {
        Member member = find(memberId);

        if (member == null) {
            return false;
        }

        return member.setEmail(email);
    }

    public boolean remove(String memberId) {
        Member member = find(memberId);

        if (member == null) {
            return false;
        }

        root = remove(root, member.getMemberId());
        size--;
        return true;
    }

    private Node remove(Node node, String memberId) {
        if (node == null) {
            return null;
        }

        int compare = memberId.compareTo(
                node.member.getMemberId()
        );

        if (compare < 0) {
            node.left = remove(node.left, memberId);
        } else if (compare > 0) {
            node.right = remove(node.right, memberId);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.member = successor.member;

            node.right = remove(
                    node.right,
                    successor.member.getMemberId()
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

    public List<Member> inorderReport() {
        List<Member> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Member> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.member);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MemberBstIndex index = new MemberBstIndex();

        System.out.println(index.add(
                new Member("M300", "Amy", "amy@mail.com")
        ));

        System.out.println(index.add(
                new Member("M100", "Ben", "ben@mail.com")
        ));

        System.out.println(index.add(
                new Member("M500", "Cindy", "cindy@mail.com")
        ));

        System.out.println(index.add(
                new Member("M200", "David", "david@mail.com")
        ));

        System.out.println(index.add(
                new Member("M400", "Eva", "eva@mail.com")
        ));

        System.out.println(index.add(
                new Member("M300", "Frank", "frank@mail.com")
        ));

        System.out.println("Find M200: "
                + index.find("M200"));

        System.out.println("Update M200: "
                + index.updateEmail(
                        "M200",
                        "david_new@mail.com"
                ));

        System.out.println("Update blank email: "
                + index.updateEmail("M200", "   "));

        System.out.println("Remove M300: "
                + index.remove("M300"));

        System.out.println("Remove M999: "
                + index.remove("M999"));

        System.out.println("Size: " + index.size());

        System.out.println("Inorder Report:");

        for (Member member : index.inorderReport()) {
            System.out.println(member);
        }
    }
}