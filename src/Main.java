import controller.LibraryController;

import model.Book;
import model.Library;
import view.LibraryView;

import java.util.List;
import java.util.Scanner;

import managers.FileManager;
import managers.StorageHandler;
import managers.LanguageManager;
import managers.SupportedLanguage;

public class Main {
    private static final String OPTION_BORROW = "1";
    private static final String OPTION_RETURN = "2";
    private static final String OPTION_SEARCH = "3";
    private static final String OPTION_ADD = "4";
    private static final String OPTION_EXIT = "5";
    private static final String OPTION_HELP = "6";
    private static final String OPTION_HELP_TEXT = "help";

    private static LanguageManager languageManager;

    public static void main(String[] args) {
        languageManager = LanguageManager.getInstance();
        selectLanguage();

        Library library = new Library();
        StorageHandler fileManager = new FileManager();
        fileManager.loadBooks(library);
        LibraryController controller = new LibraryController(library);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(languageManager.getMessage("menu.display"));
            System.out.print(languageManager.getMessage("menu.choose"));
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
                    System.out.println(languageManager.getMessage("general.goodbye"));
                    scanner.close();
                    return;
                }
                case OPTION_HELP, OPTION_HELP_TEXT -> showHelp(scanner);
                default -> System.out.println(languageManager.getMessage("general.invalid"));
            }
            System.out.println();
        }
    }

    private static void selectLanguage() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(languageManager.getMessage("language.select"));
        System.out.print(languageManager.getMessage("language.choice"));

        String choice = scanner.nextLine();

        if ("2".equals(choice)) {
            languageManager.setLanguage(SupportedLanguage.POLISH);
        } else {
            languageManager.setLanguage(SupportedLanguage.ENGLISH);
        }
    }

    private static void showHelp(Scanner scanner) {
        System.out.println(languageManager.getMessage("help.display"));
        System.out.print(languageManager.getMessage("help.press"));
        scanner.nextLine();
    }

    private static void borrowBook(LibraryController controller, Scanner scanner) {
        System.out.print(languageManager.getMessage("borrow.enter"));
        String query = scanner.nextLine();
        List<Book> results = controller.searchBooks(query);

        if (results.isEmpty()) {
            System.out.println(languageManager.getMessage("general.no_books_found"));
            return;
        }

        System.out.println(languageManager.getMessage("borrow.found"));
        System.out.println(LibraryView.showBooks(results));
        System.out.print(languageManager.getMessage("borrow.choose"));

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (isValidChoice(choice, results.size())) {
                Book selectedBook = results.get(choice - 1);
                String message = controller.borrowBook(selectedBook);
                System.out.println(message);
            } else {
                System.out.println(languageManager.getMessage("borrow.canceled"));
            }
        } catch (NumberFormatException e) {
            System.out.println(languageManager.getMessage("borrow.canceled"));
        }
    }

    private static void returnBook(LibraryController controller, Scanner scanner) {
        List<Book> borrowedBooks = controller.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println(languageManager.getMessage("return.no_books"));
            return;
        }

        System.out.println(languageManager.getMessage("return.your_books"));
        System.out.println(LibraryView.showBooks(borrowedBooks));
        System.out.print(languageManager.getMessage("return.choose"));

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (isValidChoice(choice, borrowedBooks.size())) {
                Book selectedBook = borrowedBooks.get(choice - 1);
                String message = controller.returnBook(selectedBook);
                System.out.println(message);
            } else {
                System.out.println(languageManager.getMessage("return.canceled"));
            }
        } catch (NumberFormatException e) {
            System.out.println(languageManager.getMessage("return.canceled"));
        }
    }

    private static void searchBooks(LibraryController controller, Scanner scanner) {
        System.out.print(languageManager.getMessage("search.enter"));
        String query = scanner.nextLine();
        List<Book> results = controller.searchBooks(query);
        System.out.println(LibraryView.showBooks(results));
    }

    private static void addBook(LibraryController controller, Scanner scanner) {
        System.out.print(languageManager.getMessage("add.title"));
        String title = scanner.nextLine();
        System.out.print(languageManager.getMessage("add.author"));
        String author = scanner.nextLine();
        String message = controller.addBook(title, author);
        System.out.println(message);
    }

    private static boolean isValidChoice(int choice, int listSize) {
        return choice > 0 && choice <= listSize;
    }
}
