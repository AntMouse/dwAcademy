package com.example.DWShopProject.product.enums;

public enum ParentType {
    TOPS("상의"),
    BOTTOMS("하의"),
    OUTERWEAR("아우터"),
    SHOES("신발"),
    ACCESSORIES("액세서리");

    private final String displayName;

    ParentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
