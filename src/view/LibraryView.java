package view;

import java.util.Collection;
import managers.LanguageManager;
import managers.SearchMessageKey;
import model.Book;

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
