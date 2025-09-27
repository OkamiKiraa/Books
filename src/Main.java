import controller.LibraryController;
import model.Book;
import model.Library;
import view.LibraryView;
import java.util.List;
import java.util.Scanner;
import managers.FileManager;
import managers.StorageHandler;

public class Main {
    private static final String OPTION_BORROW = "1";
    private static final String OPTION_RETURN = "2";
    private static final String OPTION_SEARCH = "3";
    private static final String OPTION_ADD = "4";
    private static final String OPTION_EXIT = "5";
    private static final String OPTION_HELP = "6";
    private static final String OPTION_HELP_TEXT = "pomoc";

    public static void main(String[] args) {
        Library library = new Library();
        StorageHandler fileManager = new FileManager();
        fileManager.loadBooks(library);
        LibraryController controller = new LibraryController(library);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(getMenu());
            System.out.print("Wybierz opcję: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case OPTION_BORROW -> borrowBook(controller, scanner);
                case OPTION_RETURN -> returnBook(controller, scanner);
                case OPTION_SEARCH -> searchBooks(controller, scanner);
                case OPTION_ADD -> {
                    addBook(controller, scanner);
                    fileManager.saveBooks(library);
                }
                case OPTION_EXIT -> {
                    fileManager.saveBooks(library);
                    System.out.println("Do zobaczenia!");
                    return;
                }
                case OPTION_HELP, OPTION_HELP_TEXT -> showHelp(scanner);
                default -> System.out.println("Nieprawidłowy wybór książki. Spróbuj ponownie.");
            }
            System.out.println();
        }
    }

    private static String getMenu() {
        return """
            MENU:
            1. Wypożycz książkę
            2. Zwróć książkę
            3. Szukaj książek
            4. Dodaj książkę do biblioteki
            5. Zakończ
            6. Pomoc""";
    }

    private static void showHelp(Scanner scanner) {
        System.out.println("=== POMOC ===");
        System.out.println("1. Wypożycz książkę - wpisz tytuł lub fragment tytułu, wybierz książkę z listy");
        System.out.println("2. Zwróć książkę - zobacz swoje wypożyczone książki i wybierz którą zwrócić");
        System.out.println("3. Szukaj książek - znajdź książki po tytule lub autorze");
        System.out.println("4. Dodaj książkę - dodaj nową książkę do biblioteki");
        System.out.println("5. Zakończ - wyjdź z aplikacji");

        System.out.print("\nNaciśnij Enter aby wrócić do menu...");
        scanner.nextLine();
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
        int choice = Integer.parseInt(scanner.nextLine());
        if (isValidChoice(choice, results.size())) {
            Book selectedBook = results.get(choice - 1);
            String message = controller.borrowBook(selectedBook);
            System.out.println(message);
        } else {
            System.out.println("Anulowano operację lub podano nieprawidłowy numer.");
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
        int choice = Integer.parseInt(scanner.nextLine());
        if (isValidChoice(choice, borrowedBooks.size())) {
            Book selectedBook = borrowedBooks.get(choice - 1);
            String message = controller.returnBook(selectedBook);
            System.out.println(message);
        } else {
            System.out.println("Anulowano operację lub podano nieprawidłowy numer.");
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

    private static boolean isValidChoice(int choice, int listSize) {
        return choice > 0 && choice <= listSize;
    }
}