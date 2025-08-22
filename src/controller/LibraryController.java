package controller;

import model.Book;
import model.Library;
import view.BookView;

import java.util.List;

public class LibraryController {

    private final Library library;

    public LibraryController(Library library) {
        this.library = library;
    }

    public List<Book> searchBooks(String query) {
        return library.searchBooks(query).stream().toList();
    }

    public List<Book> getBorrowedBooks() {
        return library.getBorrowedBooks().stream().toList();
    }

    public String borrowBook(Book book) {
        if (library.isBorrowed(book)) {
            return "Błąd: Ta książka jest już wypożyczona.";
        }
        if (library.borrowBook(book)) {

            return String.format("Pomyślnie wypożyczono: %s", BookView.showBook(book));
        }
        return "Błąd: Nie udało się wypożyczyć książki.";
    }

    public String returnBook(Book book) {
        if (library.returnBook(book)) {
            return String.format("Pomyślnie zwrócono: %s", BookView.showBook(book));
        }
        return "Błąd: Tej książki nie było na liście wypożyczonych.";
    }

    public String addBook(String title, String author) {

        if (title.isBlank() || author.isBlank()) {
            return "Błąd: Tytuł i autor nie mogą być puste.";
        }
        if (library.addBook(title, author)) {

            return String.format("Pomyślnie dodano książkę: \"%s\"", title);
        } else {
            return "Błąd: Taka książka już istnieje w bibliotece.";
        }
    }
}