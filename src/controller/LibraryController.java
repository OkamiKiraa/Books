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
        // Zalecenie Senpai - uycie stream().toList() zamiast konstruktora ArrayList
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
            return "Pomyślnie wypożyczono: " + BookView.showBook(book);
        }
        return "Błąd: Nie udało się wypożyczyć książki.";
    }

    public String returnBook(Book book) {
        if (library.returnBook(book)) {
            return "Pomyślnie zwrócono: " + BookView.showBook(book);
        }
        return "Błąd: Tej książki nie było na liście wypożyczonych.";
    }

    public String addBook(String title, String author) {
        if (title.isBlank() || author.isBlank()) {
            return "Błąd: Tytuł i autor nie mogą być puste.";
        }
        if (library.addBook(title, author)) {
            return "Pomyślnie dodano książkę: \"" + title + "\"";
        } else {
            return "Błąd: Taka książka już istnieje w bibliotece.";
        }
    }
}


// senpai mówi, że coś mi nie wyszło, więc... chodzi o to, że nazywam klasy library controller , czyli chciałbym zarządzać library, a nasze library ma books i borrow books, a to co tu mam, to tak na serio te metody oznaczają
// w library controller, ma zarządzać biblioteką, a biblioteka zarządza też książkami, bo ma swoje książki , więc my chcemy w library controller chcemy mieć operacje, aby je wykonać na bibliotece, a w mainie mieć pętle z zapytaniami do menu, bo teraz dałem , że library controller jest jednocześnie kontrolerem i viewierem (lol)
// nie robić app controller i app view, bo to ponad miarę (hmm), ale chodzi o to, że jak mam pętle RUN to ma być w MAIN,
// library controller powinien mieć połączenie z VIEW... hmm, klaps senpaiowi w tyłek? :)))))))))
// nie robić konstruktorem array list , tylko toarray, a jak chce mieć to list, stream.tolist

// REBASE , conflicts - ogarnąć w gicie... konflikty mogłyby się mocno gryźć, założeniem jest to, że zaczynając nowego taska nie zaciągam zmian ()w diealny mświecie), tylko zaciągam od dev branchu, nasz main/master jest naszym branchem releasowanym, żeby nie było kłopotu
// przerzucić METODE RUN I WYJEBAĆ Z LIBRARY CONTRELER bede mial , Z PRINT MENU fajnie byłoby dać do view, (senpai mówi, ja robię), ale raczej do maina w metodzie, metody poniżej print menu, wolimy zeby controller library wytrzucil w metodzie return, a do main co mam na samym początku ze scannera i systema początkowego
// 49 linijka , 72 linia, 92 , linia, ogarnąć library controller, zamiast konstruktora to po stworzeniu przerzucić na to list.
// dodanie zapisu i odczytu do pliku, zapis stanu library do pliku .txt albo .csv i odczyt przy starcie, dodać klasę user, zamiast na libarry borrowed books, byłby