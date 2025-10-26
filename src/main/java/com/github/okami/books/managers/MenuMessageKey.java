package com.github.okami.books.managers;

public enum MenuMessageKey implements MessageKey {
    DISPLAY("menu.display"),
    CHOOSE("menu.choose");

    private final String key;

    MenuMessageKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}