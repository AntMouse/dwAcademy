package com.example.DWShopProject.account.enums;

public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
