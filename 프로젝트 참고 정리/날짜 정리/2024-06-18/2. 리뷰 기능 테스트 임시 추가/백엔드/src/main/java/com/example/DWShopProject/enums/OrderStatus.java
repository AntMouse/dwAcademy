package com.example.DWShopProject.enums;

// 주문 상태를 나타내는 Enum 클래스
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