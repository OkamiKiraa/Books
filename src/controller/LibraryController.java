package controller;

import managers.AddMessageKey;
import managers.BorrowMessageKey;
import managers.LanguageManager;
import managers.ReturnMessageKey;

import model.Book;
import model.Library;

import view.BookView;

import java.util.List;

public class LibraryController {

    private final Library library;
    private static LanguageManager languageManager = LanguageManager.getInstance();

    public LibraryController(Library library) {
        this.library = library;
        languageManager = LanguageManager.getInstance();
    }

    public List<Book> searchBooks(String query) {
        return library.searchBooks(query).stream().toList();
    }

    public List<Book> getBorrowedBooks() {
        return library.getBorrowedBooks().stream().toList();
    }

    public String borrowBook(Book book) {
        if (library.isBorrowed(book)) {
            return languageManager.getMessage(BorrowMessageKey.ALREADY);
        }
        if (library.borrowBook(book)) {
            return languageManager.getMessage(BorrowMessageKey.SUCCESS, BookView.showBook(book));
        }
        return languageManager.getMessage(BorrowMessageKey.FAILED);
    }

    public String returnBook(Book book) {
        if (library.returnBook(book)) {
            return languageManager.getMessage(ReturnMessageKey.SUCCESS, BookView.showBook(book));
        }
        return languageManager.getMessage(ReturnMessageKey.ERROR);
    }

    public String addBook(String title, String author) {
        if (title.isBlank() || author.isBlank()) {
            return languageManager.getMessage(AddMessageKey.EMPTY);
        }
        if (library.addBook(title, author)) {
            return languageManager.getMessage(AddMessageKey.SUCCESS, title);
        } else {
            return languageManager.getMessage(AddMessageKey.EXISTS);
        }
    }
}
