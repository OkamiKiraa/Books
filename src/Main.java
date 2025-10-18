import controller.LibraryController;
import java.util.List;
import java.util.Scanner;
import managers.AddMessageKey;
import managers.BorrowMessageKey;
import managers.FileManager;
import managers.GeneralMessageKey;
import managers.HelpMessageKey;
import managers.LanguageManager;
import managers.LanguageMessageKey;
import managers.MenuMessageKey;
import managers.ReturnMessageKey;
import managers.SearchMessageKey;
import managers.StorageHandler;
import managers.SupportedLanguage;
import model.Book;
import model.Library;
import view.LibraryView;

public class Main {

    private static final String OPTION_BORROW = "1";
    private static final String OPTION_RETURN = "2";
    private static final String OPTION_SEARCH = "3";
    private static final String OPTION_ADD = "4";
    private static final String OPTION_EXIT = "5";
    private static final String OPTION_HELP = "6";
    private static final String OPTION_HELP_TEXT = "help";
    private static final String OPTION_LANGUAGE_POLISH = "2";

    private static final LanguageManager languageManager = LanguageManager.getInstance();

    public static void main(String[] args) {
        selectLanguage();

        Library library = new Library();
        StorageHandler fileManager = new FileManager();
        fileManager.loadBooks(library);
        LibraryController controller = new LibraryController(library);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(languageManager.getMessage(MenuMessageKey.DISPLAY));
            System.out.print(languageManager.getMessage(MenuMessageKey.CHOOSE));
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
                    System.out.println(languageManager.getMessage(GeneralMessageKey.GOODBYE));
                    scanner.close();
                    return;
                }
                case OPTION_HELP, OPTION_HELP_TEXT -> showHelp(scanner);
                default ->
                        System.out.println(languageManager.getMessage(GeneralMessageKey.INVALID));
            }
            System.out.println();
        }
    }

    private static void selectLanguage() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(languageManager.getMessage(LanguageMessageKey.SELECT));
        System.out.print(languageManager.getMessage(LanguageMessageKey.CHOICE));

        String choice = scanner.nextLine();

        if (OPTION_LANGUAGE_POLISH.equals(choice)) {
            languageManager.setLanguage(SupportedLanguage.POLISH);
        } else {
            languageManager.setLanguage(SupportedLanguage.ENGLISH);
        }
    }

    private static void showHelp(Scanner scanner) {
        System.out.println(languageManager.getMessage(HelpMessageKey.DISPLAY));
        System.out.print(languageManager.getMessage(HelpMessageKey.PRESS));
        scanner.nextLine();
    }

    private static void borrowBook(LibraryController controller, Scanner scanner) {
        System.out.print(languageManager.getMessage(BorrowMessageKey.ENTER));
        String query = scanner.nextLine();
        List<Book> results = controller.searchBooks(query);

        if (results.isEmpty()) {
            System.out.println(languageManager.getMessage(GeneralMessageKey.NO_BOOKS_FOUND));
            return;
        }

        System.out.println(languageManager.getMessage(BorrowMessageKey.FOUND));
        System.out.println(LibraryView.showBooks(results));
        System.out.print(languageManager.getMessage(BorrowMessageKey.CHOOSE));

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (isValidChoice(choice, results.size())) {
                Book selectedBook = results.get(choice - 1);
                String message = controller.borrowBook(selectedBook);
                System.out.println(message);
            } else {
                System.out.println(languageManager.getMessage(BorrowMessageKey.CANCELED));
            }
        } catch (NumberFormatException e) {
            System.out.println(languageManager.getMessage(BorrowMessageKey.CANCELED));
        }
    }

    private static void returnBook(LibraryController controller, Scanner scanner) {
        List<Book> borrowedBooks = controller.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println(languageManager.getMessage(ReturnMessageKey.NO_BOOKS));
            return;
        }

        System.out.println(languageManager.getMessage(ReturnMessageKey.YOUR_BOOKS));
        System.out.println(LibraryView.showBooks(borrowedBooks));
        System.out.print(languageManager.getMessage(ReturnMessageKey.CHOOSE));

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (isValidChoice(choice, borrowedBooks.size())) {
                Book selectedBook = borrowedBooks.get(choice - 1);
                String message = controller.returnBook(selectedBook);
                System.out.println(message);
            } else {
                System.out.println(languageManager.getMessage(ReturnMessageKey.CANCELED));
            }
        } catch (NumberFormatException e) {
            System.out.println(languageManager.getMessage(ReturnMessageKey.CANCELED));
        }
    }

    private static void searchBooks(LibraryController controller, Scanner scanner) {
        System.out.print(languageManager.getMessage(SearchMessageKey.ENTER));
        String query = scanner.nextLine();
        List<Book> results = controller.searchBooks(query);
        System.out.println(LibraryView.showBooks(results));
    }

    private static void addBook(LibraryController controller, Scanner scanner) {
        System.out.print(languageManager.getMessage(AddMessageKey.TITLE));
        String title = scanner.nextLine();
        System.out.print(languageManager.getMessage(AddMessageKey.AUTHOR));
        String author = scanner.nextLine();
        String message = controller.addBook(title, author);
        System.out.println(message);
    }

    private static boolean isValidChoice(int choice, int listSize) {
        return choice > 0 && choice <= listSize;
    }
}
