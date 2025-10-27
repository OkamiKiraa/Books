package com.github.okami.books.managers;

import com.github.okami.books.model.Library;

public interface StorageHandler {

    void saveBooks(Library library);

    void loadBooks(Library library);
}