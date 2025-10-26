package com.github.okami.books.controller;

import com.github.okami.books.managers.AddMessageKey;
import com.github.okami.books.managers.BorrowMessageKey;
import com.github.okami.books.managers.LanguageManager;
import com.github.okami.books.managers.ReturnMessageKey;
import com.github.okami.books.model.Book;
import com.github.okami.books.model.Library;
import com.github.okami.books.view.BookView;
import java.util.List;

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
