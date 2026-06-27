package com.example.pbl.domain;

public enum Position {
    PRESIDENT("대표"),
    VICE_PRESIDENT("부대표"),
    GENERAL("일반");

    private final String displayName;

    Position(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Position from(String text) {
        if (text == null) return null;
        for (Position p : Position.values()) {
            if (p.displayName.equals(text)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Unknown position: " + text);
    }
}
