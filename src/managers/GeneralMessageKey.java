package managers;

public enum GeneralMessageKey implements MessageKey {
    GOODBYE("general.goodbye"),
    INVALID("general.invalid"),
    NO_BOOKS_FOUND("general.no_books_found"),
    BOOK_DISPLAY("general.book_display");

    private final String key;

    GeneralMessageKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}