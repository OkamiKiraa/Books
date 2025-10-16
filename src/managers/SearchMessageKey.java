package managers;

public enum SearchMessageKey {
  ENTER("search.enter"),
  NO_RESULTS("search.no_results");

  private final String key;

  SearchMessageKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}