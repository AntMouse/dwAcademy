package com.example.DWShopProject.product.enums;

public enum ProductMainType {
    TOPS(1L, "상의"),
    BOTTOMS(2L, "하의"),
    OUTERWEAR(3L, "아우터"),
    SHOES(4L, "신발"),
    ACCESSORIES(5L, "액세서리");

    private final Long id;
    private final String displayName;

    ProductMainType(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ProductMainType getById(Long id) {
        for (ProductMainType type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}
