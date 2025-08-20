package model;

import java.util.Objects;

public class Book {
    private final String title;
    private final String author;

    private Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public static Book of(String title, String author) {
        return new Book(title, author);
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book other)) return false;
        return title.equalsIgnoreCase(other.title) &&
                author.equalsIgnoreCase(other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), author.toLowerCase());
    }
}


// Usunąłem wszystkie stepy - dla przejrzystości.