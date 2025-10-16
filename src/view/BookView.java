package view;

import model.Book;

public class BookView {
    private BookView(){}

    public static String showBook(Book book) {
        return String.format("\"%s\" by %s", book.getTitle(), book.getAuthor());
    }
}