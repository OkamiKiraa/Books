package com.github.okami.books.managers;

import java.util.Locale;

public enum SupportedLanguage {
    ENGLISH(Locale.ENGLISH),
    POLISH(Locale.of("pl"));

    private final Locale locale;

    SupportedLanguage(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}