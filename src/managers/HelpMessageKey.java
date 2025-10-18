package managers;

public enum HelpMessageKey implements MessageKey {
    DISPLAY("help.display"),
    PRESS("help.press");

    private final String key;

    HelpMessageKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}