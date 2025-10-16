package managers;

public enum HelpMessageKey {
  DISPLAY("help.display"),
  PRESS("help.press");

  private final String key;

  HelpMessageKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}