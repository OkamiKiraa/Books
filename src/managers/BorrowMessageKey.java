package managers;

public enum BorrowMessageKey implements MessageKey {
    ENTER("borrow.enter"),
    FOUND("borrow.found"),
    CHOOSE("borrow.choose"),
    SUCCESS("borrow.success"),
    ALREADY("borrow.already"),
    FAILED("borrow.failed"),
    CANCELED("borrow.canceled");

    private final String key;

    BorrowMessageKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}