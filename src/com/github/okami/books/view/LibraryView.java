package com.github.okami.books.view;

import com.github.okami.books.managers.LanguageManager;
import com.github.okami.books.managers.SearchMessageKey;
import com.github.okami.books.model.Book;
import java.util.Collection;

public class LibraryView {

    private static final LanguageManager languageManager = LanguageManager.getInstance();

    private LibraryView() {
    }

    public static String showBooks(Collection<Book> books) {
        if (books.isEmpty()) {
            return LanguageManager.getInstance().getMessage(SearchMessageKey.NO_RESULTS);
        }
        StringBuilder sb = new StringBuilder();
        int index = 1;

        for (Book book : books) {
            sb.append(index)
                    .append(". ")
                    .append(BookView.showBook(book))
                    .append("\n");
            index++;
        }
        return sb.toString();
    }
}
