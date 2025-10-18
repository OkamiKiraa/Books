package managers;

public enum SearchMessageKey implements MessageKey {
    ENTER("search.enter"),
    NO_RESULTS("search.no_results");

    private final String key;

    SearchMessageKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}