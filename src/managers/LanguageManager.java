package managers;

import java.util.Locale;
import java.util.ResourceBundle;


public class LanguageManager {
    private static LanguageManager instance;
    private ResourceBundle bundle;

    private LanguageManager() {
        setLanguage(SupportedLanguage.ENGLISH);
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    public void setLanguage(SupportedLanguage language) {
        Locale locale = language.getLocale();
        bundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(MenuMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(HelpMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(BorrowMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(BorrowMessageKey key, Object... args) {
        return getMessage(key.getKey(), args);
    }

    public String getMessage(ReturnMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(ReturnMessageKey key, Object... args) {
        return getMessage(key.getKey(), args);
    }

    public String getMessage(SearchMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(AddMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(AddMessageKey key, Object... args) {
        return getMessage(key.getKey(), args);
    }

    public String getMessage(GeneralMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(LanguageMessageKey key) {
        return getMessage(key.getKey());
    }

    public String getMessage(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            return "Missing: " + key;
        }
    }

    public String getMessage(String key, Object... args) {
        try {
            String message = bundle.getString(key);
            return String.format(message, args);
        } catch (Exception e) {
            return "Missing: " + key;
        }
    }
}
