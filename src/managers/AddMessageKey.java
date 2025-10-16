package managers;

public enum AddMessageKey {
  TITLE("add.title"),
  AUTHOR("add.author"),
  SUCCESS("add.success"),
  EXISTS("add.exists"),
  EMPTY("add.empty"),
  ERROR_LOAD("add.error.load"),
  ERROR_SAVE("add.error.save");

  private final String key;

  AddMessageKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}