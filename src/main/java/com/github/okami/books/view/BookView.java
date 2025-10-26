package com.github.okami.books.view;

import com.github.okami.books.managers.GeneralMessageKey;
import com.github.okami.books.managers.LanguageManager;
import com.github.okami.books.model.Book;

public class BookView {

    private BookView() {
    }

    public static String showBook(Book book) {
        LanguageManager languageManager = LanguageManager.getInstance();
        return languageManager.getMessage(GeneralMessageKey.BOOK_DISPLAY, book.getTitle(),
                book.getAuthor());
    }
}