package managers;

public enum LanguageMessageKey implements MessageKey {
    SELECT("language.select"),
    CHOICE("language.choice");

    private final String key;

    LanguageMessageKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}