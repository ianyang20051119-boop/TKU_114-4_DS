import java.util.ArrayList;
import java.util.List;

public class LibraryBookBst {

    static class Book {
        private String isbn;
        private String title;
        private String author;
        private boolean available;

        public Book(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        public boolean borrow() {
            if (!available) {
                return false;
            }

            available = false;
            return true;
        }

        public boolean returnBook() {
            if (available) {
                return false;
            }

            available = true;
            return true;
        }

        @Override
        public String toString() {
            return isbn + " " + title + " " + author
                    + " available=" + available;
        }
    }

    static class Node {
        Book book;
        Node left;
        Node right;

        Node(Book book) {
            this.book = book;
        }
    }

    private Node root;
    private int size;

    public boolean add(Book book) {
        if (book == null || book.getIsbn() == null) {
            return false;
        }

        if (root == null) {
            root = new Node(book);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int compare = book.getIsbn()
                    .compareTo(current.book.getIsbn());

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {
                if (current.left == null) {
                    current.left = new Node(book);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(book);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Book find(String isbn) {
        if (isbn == null) {
            return null;
        }

        Node current = root;

        while (current != null) {
            int compare = isbn.compareTo(
                    current.book.getIsbn()
            );

            if (compare == 0) {
                return current.book;
            }

            if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean borrow(String isbn) {
        Book book = find(isbn);

        if (book == null) {
            return false;
        }

        return book.borrow();
    }

    public boolean returnBook(String isbn) {
        Book book = find(isbn);

        if (book == null) {
            return false;
        }

        return book.returnBook();
    }

    public boolean remove(String isbn) {
        Book book = find(isbn);

        if (book == null) {
            return false;
        }

        if (!book.isAvailable()) {
            return false;
        }

        root = remove(root, isbn);
        size--;
        return true;
    }

    private Node remove(Node node, String isbn) {
        if (node == null) {
            return null;
        }

        int compare = isbn.compareTo(
                node.book.getIsbn()
        );

        if (compare < 0) {
            node.left = remove(node.left, isbn);
        } else if (compare > 0) {
            node.right = remove(node.right, isbn);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.book = successor.book;

            node.right = remove(
                    node.right,
                    successor.book.getIsbn()
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

    public List<Book> rangeQuery(
            String lowIsbn,
            String highIsbn) {

        List<Book> result = new ArrayList<>();

        if (lowIsbn == null || highIsbn == null) {
            return result;
        }

        if (lowIsbn.compareTo(highIsbn) > 0) {
            return result;
        }

        rangeQuery(root, lowIsbn, highIsbn, result);
        return result;
    }

    private void rangeQuery(
            Node node,
            String lowIsbn,
            String highIsbn,
            List<Book> result) {

        if (node == null) {
            return;
        }

        String isbn = node.book.getIsbn();

        if (isbn.compareTo(lowIsbn) > 0) {
            rangeQuery(
                    node.left,
                    lowIsbn,
                    highIsbn,
                    result
            );
        }

        if (isbn.compareTo(lowIsbn) >= 0
                && isbn.compareTo(highIsbn) <= 0) {
            result.add(node.book);
        }

        if (isbn.compareTo(highIsbn) < 0) {
            rangeQuery(
                    node.right,
                    lowIsbn,
                    highIsbn,
                    result
            );
        }
    }

    public List<Book> inorderReport() {
        List<Book> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(
            Node node,
            List<Book> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.book);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LibraryBookBst library = new LibraryBookBst();

        System.out.println(library.add(
                new Book("ISBN300", "Java Programming", "Amy")
        ));

        System.out.println(library.add(
                new Book("ISBN100", "Data Structures", "Ben")
        ));

        System.out.println(library.add(
                new Book("ISBN500", "Algorithms", "Cindy")
        ));

        System.out.println(library.add(
                new Book("ISBN200", "Database Systems", "David")
        ));

        System.out.println(library.add(
                new Book("ISBN400", "Computer Networks", "Eva")
        ));

        System.out.println(library.add(
                new Book("ISBN300", "Duplicate Book", "Frank")
        ));

        System.out.println();

        System.out.println(
                "Find ISBN200: "
                        + library.find("ISBN200")
        );

        System.out.println(
                "Find ISBN999: "
                        + library.find("ISBN999")
        );

        System.out.println();

        System.out.println(
                "Borrow ISBN200: "
                        + library.borrow("ISBN200")
        );

        System.out.println(
                "Borrow ISBN200 again: "
                        + library.borrow("ISBN200")
        );

        System.out.println(
                "Remove borrowed ISBN200: "
                        + library.remove("ISBN200")
        );

        System.out.println(
                "Return ISBN200: "
                        + library.returnBook("ISBN200")
        );

        System.out.println(
                "Remove ISBN200: "
                        + library.remove("ISBN200")
        );

        System.out.println();

        System.out.println("Range ISBN100 ~ ISBN400:");

        for (Book book :
                library.rangeQuery("ISBN100", "ISBN400")) {
            System.out.println(book);
        }

        System.out.println();

        System.out.println("Inorder Report:");

        for (Book book : library.inorderReport()) {
            System.out.println(book);
        }

        System.out.println();

        System.out.println("Size: " + library.size());
    }
}