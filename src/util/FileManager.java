package util;

import model.Book;
import model.Library;
import java.io.*;
import java.util.Set;

public class FileManager {
    private static final String BOOKS_FILE = "books.txt";

    public static void saveBooks(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            Set<Book> allBooks = library.getAllBooks();
            for (Book book : allBooks) {
                writer.println(book.getTitle() + "|" + book.getAuthor());
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania książek: " + e.getMessage());
        }
    }

    public static void loadBooks(Library library) {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    library.addBook(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas ładowania książek: " + e.getMessage());
        }
    }
}