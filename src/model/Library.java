package model;

import java.util.*;

public class Library {
    private final Set<Book> books = new HashSet<>();
    private final Set<Book> borrowedBooks = new HashSet<>();

    public boolean addBook(String title, String author) {
        return books.add(Book.of(title, author));
    }

    public boolean borrowBook(Book book) {
        if (!books.contains(book)) return false;
        if (borrowedBooks.contains(book)) return false;
        borrowedBooks.add(book);
        return true;
    }

    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    public Set<Book> searchBooks(String query) {
        String lower = query.toLowerCase();
        Set<Book> matches = new HashSet<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(lower) ||
                    b.getAuthor().toLowerCase().contains(lower)) {
                matches.add(b);
            }
        }
        return matches;
    }

    public Set<Book> getBorrowedBooks() {
        return new HashSet<>(borrowedBooks);
    }

    public boolean isBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }
}
