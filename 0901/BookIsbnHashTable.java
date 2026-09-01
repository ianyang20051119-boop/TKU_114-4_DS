import java.util.ArrayList;
import java.util.List;

public class BookIsbnHashTable {

    static class Book {
        private String isbn;
        private String title;
        private String author;

        public Book(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
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

        public void update(String title, String author) {
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() {
            return isbn + "|" + title + "|" + author;
        }
    }

    private final List<Book>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public BookIsbnHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException();
        }

        buckets = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        size = 0;
    }

    private int index(String isbn) {
        return Math.floorMod(isbn.hashCode(), buckets.length);
    }

    public boolean put(Book book) {
        if (book == null
                || book.getIsbn() == null
                || book.getIsbn().trim().isEmpty()) {
            return false;
        }

        String isbn = book.getIsbn().trim();
        int index = index(isbn);

        for (Book existing : buckets[index]) {
            if (existing.getIsbn().equals(isbn)) {
                existing.update(
                        book.getTitle(),
                        book.getAuthor()
                );
                return false;
            }
        }

        buckets[index].add(book);
        size++;
        return true;
    }

    public Book get(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }

        isbn = isbn.trim();
        int index = index(isbn);

        for (Book book : buckets[index]) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    public boolean update(
            String isbn, String title, String author) {

        Book book = get(isbn);

        if (book == null) {
            return false;
        }

        book.update(title, author);
        return true;
    }

    public boolean remove(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }

        isbn = isbn.trim();
        int index = index(isbn);

        for (int i = 0; i < buckets[index].size(); i++) {
            if (buckets[index].get(i).getIsbn().equals(isbn)) {
                buckets[index].remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return (double) size / buckets.length;
    }

    public void bucketReport() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.println(
                    "bucket " + i + ": " + buckets[i]
            );
        }
    }

    public static void main(String[] args) {

        BookIsbnHashTable table =
                new BookIsbnHashTable(5);

        System.out.println(
                "add: " + table.put(
                        new Book(
                                "978001",
                                "Java Basics",
                                "Amy"
                        )
                )
        );

        System.out.println(
                "add: " + table.put(
                        new Book(
                                "978002",
                                "Data Structures",
                                "Ben"
                        )
                )
        );

        System.out.println(
                "add: " + table.put(
                        new Book(
                                "978003",
                                "Algorithms",
                                "Cara"
                        )
                )
        );

        System.out.println(
                "add: " + table.put(
                        new Book(
                                "978004",
                                "Database",
                                "David"
                        )
                )
        );

        System.out.println(
                "add: " + table.put(
                        new Book(
                                "978005",
                                "Networks",
                                "Eva"
                        )
                )
        );

        System.out.println();

        System.out.println("size: " + table.size());

        System.out.printf(
                "load factor: %.2f%n",
                table.loadFactor()
        );

        System.out.println(
                "search 978003: " + table.get("978003")
        );

        System.out.println(
                "search 999999: " + table.get("999999")
        );

        System.out.println();

        System.out.println(
                "update 978003: "
                + table.update(
                        "978003",
                        "Advanced Algorithms",
                        "Cara"
                )
        );

        System.out.println(
                "after update: " + table.get("978003")
        );

        System.out.println();

        System.out.println(
                "same ISBN put: "
                + table.put(
                        new Book(
                                "978002",
                                "Advanced Data Structures",
                                "Bob"
                        )
                )
        );

        System.out.println(
                "978002: " + table.get("978002")
        );

        System.out.println(
                "size after same ISBN: " + table.size()
        );

        System.out.println();

        System.out.println(
                "remove 978004: "
                + table.remove("978004")
        );

        System.out.println(
                "remove 999999: "
                + table.remove("999999")
        );

        System.out.println();

        System.out.println("size: " + table.size());

        System.out.printf(
                "load factor: %.2f%n",
                table.loadFactor()
        );

        System.out.println();

        System.out.println("Bucket Report:");
        table.bucketReport();
    }
}