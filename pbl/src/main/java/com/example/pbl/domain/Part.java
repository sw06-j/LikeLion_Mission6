package com.example.pbl.domain;

public enum Part {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    DESIGN("기획디자인");

    private final String displayName;

    Part(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Part from(String text) {
        if (text == null) return null;
        for (Part p : Part.values()) {
            if (p.displayName.equals(text)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Unknown part: " + text);
    }
}
