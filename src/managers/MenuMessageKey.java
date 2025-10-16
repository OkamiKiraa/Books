package managers;

public enum MenuMessageKey {
  DISPLAY("menu.display"),
  CHOOSE("menu.choose");

  private final String key;

  MenuMessageKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}