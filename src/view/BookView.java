package view;

import model.Book;

public class BookView {
    public static String showBook(Book book) {
        return "\"" + book.getTitle() + "\" by " + book.getAuthor();
    }
}
