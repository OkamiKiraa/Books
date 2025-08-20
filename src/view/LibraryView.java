package view;

import model.Book;
import java.util.List;

public class LibraryView {
    public static String showBooks(List<Book> books) {
        if (books.isEmpty()) return "Brak książek.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            sb.append(i + 1).append(". ")
                    .append(BookView.showBook(books.get(i)))
                    .append("\n");
        }
        return sb.toString();
    }
}
