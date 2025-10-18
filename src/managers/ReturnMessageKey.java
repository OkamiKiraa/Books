package managers;

public enum ReturnMessageKey implements MessageKey{
    NO_BOOKS("return.no_books"),
    YOUR_BOOKS("return.your_books"),
    CHOOSE("return.choose"),
    SUCCESS("return.success"),
    ERROR("return.error"),
    CANCELED("return.canceled");

    private final String key;

    ReturnMessageKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}