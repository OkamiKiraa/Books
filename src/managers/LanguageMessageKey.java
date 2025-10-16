package managers;

public enum LanguageMessageKey {
  SELECT("language.select"),
  CHOICE("language.choice");

  private final String key;

  LanguageMessageKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}