package controller;

import managers.LanguageManager;

import model.Book;
import model.Library;

import view.BookView;

import java.util.List;

public class LibraryController {

    private final Library library;
    private final LanguageManager languageManager;

    public LibraryController(Library library) {
        this.library = library;
        this.languageManager = LanguageManager.getInstance();
    }

    public List<Book> searchBooks(String query) {
        return library.searchBooks(query).stream().toList();
    }

    public List<Book> getBorrowedBooks() {
        return library.getBorrowedBooks().stream().toList();
    }

    public String borrowBook(Book book) {
        if (library.isBorrowed(book)) {
            return languageManager.getMessage("borrow.already");
        }
        if (library.borrowBook(book)) {
            return languageManager.getMessage("borrow.success", BookView.showBook(book));
        }
        return languageManager.getMessage("borrow.failed");
    }

    public String returnBook(Book book) {
        if (library.returnBook(book)) {
            return languageManager.getMessage("return.success", BookView.showBook(book));
        }
        return languageManager.getMessage("return.error");
    }

    public String addBook(String title, String author) {
        if (title.isBlank() || author.isBlank()) {
            return languageManager.getMessage("add.empty");
        }
        if (library.addBook(title, author)) {
            return languageManager.getMessage("add.success", title);
        } else {
            return languageManager.getMessage("add.exists");
        }
    }
}
