package view;

import managers.GeneralMessageKey;
import managers.LanguageManager;

import model.Book;

public class BookView {

    private BookView() {
    }

    public static String showBook(Book book) {
        LanguageManager languageManager = LanguageManager.getInstance();
        return languageManager.getMessage(GeneralMessageKey.BOOK_DISPLAY, book.getTitle(), book.getAuthor());
    }
}