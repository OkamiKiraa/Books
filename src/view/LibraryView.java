package view;

import managers.LanguageManager;
import managers.SearchMessageKey;

import model.Book;

import java.util.Collection;

public class LibraryView {
    private LibraryView(){}
    private static final LanguageManager languageManager = LanguageManager.getInstance();

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
