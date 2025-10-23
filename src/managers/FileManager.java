package managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import model.Book;
import model.Library;

public class FileManager implements StorageHandler {

    private static final String BOOKS_FILE = "books.txt";
    private static final char SEPARATOR = '|';

    public void saveBooks(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            Set<Book> allBooks = library.getAllBooks();
            for (Book book : allBooks) {
                writer.println(book.getTitle() + SEPARATOR + book.getAuthor());
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

        CsvParser parser = new CsvParser(SEPARATOR);
        try {
            List<String[]> records = parser.parse(BOOKS_FILE);
            for (String[] record : records) {
                if (record.length == 2) {
                    String title = record[0];
                    String author = record[1];
                    library.addBook(title, author);
                }
            }
        } catch (IOException e) {
            System.err.println(AddMessageKey.ERROR_LOAD + e.getMessage());
        }
    }
}