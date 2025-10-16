package managers;

import model.Book;
import model.Library;

import java.io.*;
import java.util.Set;

public class FileManager implements StorageHandler {
    private static final String BOOKS_FILE = "books.txt";

    public void saveBooks(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            Set<Book> allBooks = library.getAllBooks();
            for (Book book : allBooks) {
                writer.println(book.getTitle() + "|" + book.getAuthor());
            }
        } catch (IOException e) {
            System.out.println(AddMessageKey.ERROR_SAVE + e.getMessage());
        }
    }

    public void loadBooks(Library library) {
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
            System.out.println(AddMessageKey.ERROR_LOAD + e.getMessage());
        }
    }
}