package view;

import model.Book;

import java.util.Collection;

public class LibraryView {

    public static String showBooks(Collection<Book> books) {
        if (books.isEmpty()) {
            return "Brak książek spełniających kryteria.";
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