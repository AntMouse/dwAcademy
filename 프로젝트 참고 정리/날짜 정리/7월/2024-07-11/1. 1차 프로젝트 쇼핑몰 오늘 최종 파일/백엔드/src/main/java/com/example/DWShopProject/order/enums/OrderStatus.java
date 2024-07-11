package com.example.DWShopProject.order.enums;

public enum OrderStatus {
    PENDING("배송준비"),
    COMPLETED("배송완료");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
