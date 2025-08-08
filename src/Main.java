import java.util.*;

public class Main {
    private static final String YES = "TAK";
    private static final String NO = "NIE";

    private static final String OPTION_BORROW = "1";
    private static final String OPTION_RETURN = "2";
    private static final String OPTION_SEARCH = "3";
    private static final String OPTION_ADD = "4";
    private static final String OPTION_EXIT = "5";

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        importedBooks(library);
        loop(scanner, library);
    }

    private static void loop(Scanner scanner, Library library) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case OPTION_BORROW -> borrowBookMenu(scanner, library);
                case OPTION_RETURN -> returnBookMenu(scanner, library);
                case OPTION_SEARCH -> searchBooksMenu(scanner, library);
                case OPTION_ADD -> addBookMenu(scanner, library);
                case OPTION_EXIT -> {
                    System.out.println("Do zobaczenia!");
                    return;
                }
                default -> System.out.println("Nieprawidłowy wybór.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
            MENU:
            1. Wypożycz książkę
            2. Zwróć książkę
            3. Szukaj książek
            4. Dodaj książkę do biblioteki
            5. Zakończ""");

        System.out.print("Wybierz opcję: ");
    }

    private static void borrowBookMenu(Scanner scanner, Library library) {
        while (true) {
            System.out.print("Podaj tytuł książki do wypożyczenia: ");
            String borrowTitle = scanner.nextLine();
            List<Book> matchedBooks = new ArrayList<>(library.searchBooks(borrowTitle));

            if (matchedBooks.isEmpty()) {
                System.out.println("Nie znaleziono książki.");
            } else if (matchedBooks.size() == 1) {
                confirmAndBorrow(scanner, library, matchedBooks.get(0));
            } else {
                chooseAndBorrow(scanner, library, matchedBooks);
            }

            System.out.print(String.format("Czy chcesz wypożyczyć kolejną książkę? (%s/%s): ", YES, NO));
            if (!scanner.nextLine().trim().equalsIgnoreCase(YES)) break;
        }
    }

    private static void confirmAndBorrow(Scanner scanner, Library library, Book book) {
        if (library.isBorrowed(book)) {
            System.out.println(String.format("Ta książka jest już wypożyczona: %s", book));
        } else {
            System.out.println(String.format("Znaleziono książkę: %s", book));
            System.out.print(String.format("Czy chcesz ją wypożyczyć? (%s/%s): ", YES, NO));
            if (scanner.nextLine().trim().equalsIgnoreCase(YES)) {
                library.borrowBook(book);
            } else {
                System.out.println("Anulowano wypożyczenie.");
            }
        }
    }

    private static void chooseAndBorrow(Scanner scanner, Library library, List<Book> matchedBooks) {
        System.out.println("Znaleziono kilka książek pasujących do tytułu:");
        for (int i = 0; i < matchedBooks.size(); i++) {
            String status = library.isBorrowed(matchedBooks.get(i)) ? " (wypożyczona)" : "";
            System.out.println(String.format("%d. %s%s", i + 1, matchedBooks.get(i), status));
        }

        while (true) {
            System.out.print("Wybierz numer książki do wypożyczenia lub wpisz 0, aby wrócić: ");
            try {
                int choiceNum = Integer.parseInt(scanner.nextLine());
                if (choiceNum == 0) break;
                if (choiceNum >= 1 && choiceNum <= matchedBooks.size()) {
                    confirmAndBorrow(scanner, library, matchedBooks.get(choiceNum - 1));
                    break;
                } else {
                    System.out.println("Nieprawidłowy numer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź poprawny numer.");
            }
        }
    }

    private static void returnBookMenu(Scanner scanner, Library library) {
        List<Book> borrowed = new ArrayList<>(library.getBorrowedBooks());
        if (borrowed.isEmpty()) {
            System.out.println("Nie masz wypożyczonych książek.");
            return;
        }

        System.out.println("Twoje wypożyczone książki:");
        for (int i = 0; i < borrowed.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, borrowed.get(i)));
        }

        System.out.print("Wpisz numer książki do zwrotu lub fragment tytułu: ");
        String input = scanner.nextLine();

        try {
            int num = Integer.parseInt(input);
            if (num >= 1 && num <= borrowed.size()) {
                library.returnBook(borrowed.get(num - 1));
            } else {
                System.out.println("Nieprawidłowy numer.");
            }
        } catch (NumberFormatException e) {
            List<Book> matches = new ArrayList<>(library.searchBorrowedByTitle(input));
            if (matches.isEmpty()) {
                System.out.println("Nie znaleziono wypożyczonej książki pasującej do tego tytułu.");
            } else if (matches.size() == 1) {
                library.returnBook(matches.get(0));
            } else {
                System.out.println("Znaleziono kilka pasujących książek:");
                for (int i = 0; i < matches.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, matches.get(i)));
                }
                System.out.print("Wybierz numer książki do zwrotu: ");
                try {
                    int choiceNum = Integer.parseInt(scanner.nextLine());
                    if (choiceNum >= 1 && choiceNum <= matches.size()) {
                        library.returnBook(matches.get(choiceNum - 1));
                    } else {
                        System.out.println("Nieprawidłowy numer.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Nieprawidłowe dane.");
                }
            }
        }
    }

    private static void searchBooksMenu(Scanner scanner, Library library) {
        System.out.print("Podaj fragment tytułu lub autora: ");
        String searchTerm = scanner.nextLine();
        List<Book> results = new ArrayList<>(library.searchBooks(searchTerm));

        if (results.isEmpty()) {
            System.out.println("Brak książek pasujących do zapytania.");
        } else {
            System.out.println("Znalezione książki:");
            for (int i = 0; i < results.size(); i++) {
                String status = library.isBorrowed(results.get(i)) ? " (wypożyczona)" : "";
                System.out.println(String.format("%d. %s%s", i + 1, results.get(i), status));
            }

            while (true) {
                System.out.print("Wybierz numer książki do wypożyczenia lub wpisz 0, aby wrócić do menu: ");
                try {
                    int choiceNum = Integer.parseInt(scanner.nextLine());
                    if (choiceNum == 0) break;
                    if (choiceNum >= 1 && choiceNum <= results.size()) {
                        confirmAndBorrow(scanner, library, results.get(choiceNum - 1));
                        break;
                    } else {
                        System.out.println("Nieprawidłowy numer.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Wprowadź poprawny numer.");
                }
            }
        }
    }

    private static void addBookMenu(Scanner scanner, Library library) {
        System.out.print("Podaj tytuł nowej książki: ");
        String newTitle = scanner.nextLine();
        System.out.print("Podaj autora nowej książki: ");
        String newAuthor = scanner.nextLine();

        if (library.addBook(newTitle, newAuthor)) {
            System.out.println(String.format("Dodano książkę: \"%s\" by %s", newTitle, newAuthor));
        } else {
            System.out.println("Książka o takim tytule i autorze już istnieje w bibliotece...");
        }
    }

    private static void importedBooks(Library library) {
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