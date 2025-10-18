package controller;

import java.util.List;
import managers.AddMessageKey;
import managers.BorrowMessageKey;
import managers.LanguageManager;
import managers.ReturnMessageKey;
import model.Book;
import model.Library;
import view.BookView;

public class LibraryController {

    private static final LanguageManager languageManager = LanguageManager.getInstance();
    private final Library library;

    public LibraryController(Library library) {
        this.library = library;
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
