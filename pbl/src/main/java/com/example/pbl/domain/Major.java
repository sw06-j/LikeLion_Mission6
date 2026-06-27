package com.example.pbl.domain;

public enum Major {
    COMPUTER_SCIENCE("컴퓨터공학과"),
    SOFTWARE("소프트웨어학과"),
    INFORMATION_COMMUNICATION("정보통신공학과");

    private final String displayName;

    Major(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Major from(String text) {
        if (text == null) return null;
        for (Major m : Major.values()) {
            if (m.displayName.equals(text)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Unknown major: " + text);
    }
}
