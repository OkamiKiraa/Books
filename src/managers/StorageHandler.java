package managers;

import model.Library;

public interface StorageHandler {
    void saveBooks(Library library);
    void loadBooks(Library library);
}