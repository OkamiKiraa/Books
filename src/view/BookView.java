package view;

import model.Book;

public class BookView {
    public static String showBook(Book book) {
        return "\"" + book.getTitle() + "\" by " + book.getAuthor();
    }
}




// ZMIENIC NA STRING FORMAT
// lepiej by było, żeby zwracało z pojedyńczej, a library view powinien korzystać z bookview...
//