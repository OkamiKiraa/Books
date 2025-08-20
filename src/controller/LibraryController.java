package controller;

import model.*;
import view.*;

import java.util.*;

public class LibraryController {
    private final Library library;
    private final Scanner scanner = new Scanner(System.in);

    public LibraryController(Library library) {
        this.library = library;
    }

    public void run() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> borrowBookMenu();
                case "2" -> returnBookMenu();
                case "3" -> searchBooksMenu();
                case "4" -> addBookMenu();
                case "5" -> {
                    System.out.println("Do zobaczenia!");
                    return;
                }
                default -> System.out.println("Nieprawidłowy wybór.");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
            MENU:
            1. Wypożycz książkę
            2. Zwróć książkę
            3. Szukaj książek
            4. Dodaj książkę do biblioteki
            5. Zakończ""");
        System.out.print("Wybierz opcję: ");
    }

    private void borrowBookMenu() {
        System.out.print("Podaj tytuł książki: ");
        String title = scanner.nextLine();
        List<Book> results = new ArrayList<>(library.searchBooks(title));

        if (results.isEmpty()) {
            System.out.println("Nie znaleziono książki.");
            return;
        }
        System.out.println("Znalezione książki:");
        System.out.println(LibraryView.showBooks(results));

        System.out.print("Wybierz numer do wypożyczenia (0 = anuluj): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice >= 1 && choice <= results.size()) {
            Book book = results.get(choice - 1);
            if (library.isBorrowed(book)) {
                System.out.println("Książka już wypożyczona.");
            } else {
                library.borrowBook(book);
                System.out.println("Wypożyczono: " + BookView.showBook(book));
            }
        }
    }

    private void returnBookMenu() {
        List<Book> borrowed = new ArrayList<>(library.getBorrowedBooks());
        if (borrowed.isEmpty()) {
            System.out.println("Nie masz wypożyczonych książek.");
            return;
        }
        System.out.println("Twoje wypożyczone książki:");
        System.out.println(LibraryView.showBooks(borrowed));

        System.out.print("Wybierz numer książki do zwrotu: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice >= 1 && choice <= borrowed.size()) {
            Book book = borrowed.get(choice - 1);
            library.returnBook(book);
            System.out.println("Zwrócono: " + BookView.showBook(book));
        }
    }

    private void searchBooksMenu() {
        System.out.print("Podaj tytuł lub autora: ");
        String query = scanner.nextLine();
        List<Book> results = new ArrayList<>(library.searchBooks(query));

        System.out.println(LibraryView.showBooks(results));
    }

    private void addBookMenu() {
        System.out.print("Tytuł: ");
        String title = scanner.nextLine();
        System.out.print("Autor: ");
        String author = scanner.nextLine();

        if (library.addBook(title, author)) {
            System.out.println("Dodano książkę: " + title);
        } else {
            System.out.println("Taka książka już istnieje.");
        }
    }
}
