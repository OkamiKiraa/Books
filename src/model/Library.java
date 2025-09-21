package model;

import java.util.*;

public class Library {

    private final Set<Book> books = new HashSet<>();
    private final Set<Book> borrowedBooks = new HashSet<>();

    public boolean addBook(String title, String author) {
        return books.add(Book.of(title, author));
    }

    public boolean borrowBook(Book book) {
        if (!books.contains(book)) {
            return false;
        }
        if (borrowedBooks.contains(book)) {
            return false;
        }
        borrowedBooks.add(book);
        return true;
    }

    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    public Set<Book> searchBooks(String query) {
        String lowerQuery = query.toLowerCase();
        Set<Book> matches = new HashSet<>();
        for (Book book : books) {
            if (bookMatchesQuery(book, lowerQuery)) {
                matches.add(book);
            }
        }
        return matches;
    }

    private static boolean bookMatchesQuery(Book book, String lowerQuery) {
        return book.getTitle().toLowerCase().contains(lowerQuery) ||
                book.getAuthor().toLowerCase().contains(lowerQuery);
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean isBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    public Set<Book> getAllBooks() {
        return books;
    }
}
