import controller.LibraryController;
import model.Book;
import model.Library;
import view.LibraryView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        importBooks(library);
        LibraryController controller = new LibraryController(library);
        Scanner scanner = new Scanner(System.in);

        // Główna pętla aplikacji...
        while (true) {
            System.out.println(LibraryView.getMenu());
            System.out.print("Wybierz opcję: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> borrowBook(controller, scanner);
                case "2" -> returnBook(controller, scanner);
                case "3" -> searchBooks(controller, scanner);
                case "4" -> addBook(controller, scanner);
                case "5" -> {
                    System.out.println("Do zobaczenia!");
                    return;
                }
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
            System.out.println();
        }
    }

    private static void borrowBook(LibraryController controller, Scanner scanner) {
        System.out.print("Podaj tytuł książki do wyszukania: ");
        String query = scanner.nextLine();
        List<Book> results = controller.searchBooks(query);

        if (results.isEmpty()) {
            System.out.println("Nie znaleziono książki pasującej do zapytania.");
            return;
        }

        System.out.println("Znalezione książki:");
        System.out.println(LibraryView.showBooks(results));

        System.out.print("Wybierz numer książki do wypożyczenia (0 = anuluj): ");
        int bookIndex = Integer.parseInt(scanner.nextLine());

        if (bookIndex > 0 && bookIndex <= results.size()) {
            Book selectedBook = results.get(bookIndex - 1);
            String message = controller.borrowBook(selectedBook);
            System.out.println(message);
        } else {
            System.out.println("Anulowano operację.");
        }
    }

    private static void returnBook(LibraryController controller, Scanner scanner) {
        List<Book> borrowedBooks = controller.getBorrowedBooks();

        if (borrowedBooks.isEmpty()) {
            System.out.println("Nie masz aktualnie żadnych wypożyczonych książek.");
            return;
        }

        System.out.println("Twoje wypożyczone książki:");
        System.out.println(LibraryView.showBooks(borrowedBooks));

        System.out.print("Wybierz numer książki do zwrotu (0 = anuluj): ");
        int bookIndex = Integer.parseInt(scanner.nextLine());

        if (bookIndex > 0 && bookIndex <= borrowedBooks.size()) {
            Book selectedBook = borrowedBooks.get(bookIndex - 1);
            String message = controller.returnBook(selectedBook);
            System.out.println(message);
        } else {
            System.out.println("Anulowano operację.");
        }
    }

    private static void searchBooks(LibraryController controller, Scanner scanner) {
        System.out.print("Podaj tytuł lub autora do wyszukania: ");
        String query = scanner.nextLine();
        List<Book> results = controller.searchBooks(query);
        System.out.println(LibraryView.showBooks(results));
    }

    private static void addBook(LibraryController controller, Scanner scanner) {
        System.out.print("Podaj tytuł nowej książki: ");
        String title = scanner.nextLine();
        System.out.print("Podaj autora nowej książki: ");
        String author = scanner.nextLine();

        String message = controller.addBook(title, author);
        System.out.println(message);
    }

    private static void importBooks(Library library) {
        library.addBook("To Kill a Mockingbird", "Harper Lee");
        library.addBook("1984", "George Orwell");
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        library.addBook("Pride and Prejudice", "Jane Austen");
        library.addBook("The Catcher in the Rye", "J.D. Salinger");
        library.addBook("Moby-Dick", "Herman Melville");
        library.addBook("War and Peace", "Leo Tolstoy");
        library.addBook("Crime and Punishment", "Fyodor Dostoevsky");
        library.addBook("The Hobbit", "J.R.R. Tolkien");
        library.addBook("The Lord of the Rings", "J.R.R. Tolkien");
        library.addBook("Brave New World", "Aldous Huxley");
        library.addBook("The Picture of Dorian Gray", "Oscar Wilde");
        library.addBook("Fahrenheit 451", "Ray Bradbury");
        library.addBook("The Alchemist", "Paulo Coelho");
        library.addBook("The Book Thief", "Markus Zusak");
        library.addBook("The Chronicles of Narnia", "C.S. Lewis");
        library.addBook("Dracula", "Bram Stoker");
        library.addBook("Frankenstein", "Mary Shelley");
        library.addBook("The Hitchhiker’s Guide to the Galaxy", "Douglas Adams");
        library.addBook("The Shining", "Stephen King");
    }
}
