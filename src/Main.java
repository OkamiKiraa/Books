import controller.LibraryController;
import model.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        importBooks(library);

        LibraryController controller = new LibraryController(library);
        controller.run();
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
