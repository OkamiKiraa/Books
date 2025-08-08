import java.util.*;

public class Library {
    private final Set<Book> books = new HashSet<>();
    private final Set<Book> borrowedBooks = new HashSet<>();

    public boolean addBook(String title, String author) {
        return books.add(Book.of(title, author));
    }

    public boolean borrowBook(Book book) {
        if (!books.contains(book)) {
            System.out.println("Nie znaleziono tej książki w bibliotece.");
            return false;
        }
        if (borrowedBooks.contains(book)) {
            System.out.println(String.format("Książka jest już wypożyczona: %s", book));
            return false;
        }
        borrowedBooks.add(book);
        System.out.println(String.format("Wypożyczono: %s", book));
        return true;
    }

    public boolean returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            System.out.println("Książka nie była wypożyczona.");
            return false;
        }
        borrowedBooks.remove(book);
        System.out.println(String.format("Zwrócono książkę: %s", book));
        return true;
    }

    public Set<Book> searchBooks(String query) {
        String lowerQuery = query.toLowerCase();
        Set<Book> matchingBooks = new HashSet<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(lowerQuery) ||
                    book.getTitle().toLowerCase().contains(lowerQuery)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public Set<Book> getBorrowedBooks() {
        return new HashSet<>(borrowedBooks);
    }

    public Set<Book> searchBorrowedByTitle(String fragment) {
        String lower = fragment.toLowerCase();
        Set<Book> results = new HashSet<>();
        for (Book book : borrowedBooks) {
            if (book.getTitle().toLowerCase().contains(lower)) {
                results.add(book);
            }
        }
        return results;
    }

    public boolean isBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }
}