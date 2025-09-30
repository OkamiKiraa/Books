package managers;

import java.util.Locale;
import java.util.ResourceBundle;


public class LanguageManager {
    private static LanguageManager instance;
    private ResourceBundle bundle;

    LanguageManager() {

        setLanguage(SupportedLanguage.ENGLISH);
    }

    public static synchronized LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    public void setLanguage(SupportedLanguage language) {
        Locale locale = language.getLocale();
        bundle = ResourceBundle.getBundle("messages", locale);
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
