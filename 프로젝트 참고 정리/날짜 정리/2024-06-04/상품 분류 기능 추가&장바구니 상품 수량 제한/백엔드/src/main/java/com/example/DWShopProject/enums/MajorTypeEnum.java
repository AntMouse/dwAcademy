package com.example.DWShopProject.enums;

public enum MajorTypeEnum {
    TOPS("상의"),
    BOTTOMS("하의"),
    OUTERWEAR("아우터"),
    SHOES("신발"),
    ACCESSORIES("액세서리");

    private final String displayName;

    MajorTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
