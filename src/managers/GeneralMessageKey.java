package managers;

public enum GeneralMessageKey {
  GOODBYE("general.goodbye"),
  INVALID("general.invalid"),
  NO_BOOKS_FOUND("general.no_books_found");

  private final String key;

  GeneralMessageKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}