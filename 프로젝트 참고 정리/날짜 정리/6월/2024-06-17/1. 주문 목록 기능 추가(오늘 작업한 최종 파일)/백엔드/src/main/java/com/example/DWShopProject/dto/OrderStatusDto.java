package com.example.DWShopProject.dto;

// 주문 상태와 그에 해당하는 displayName을 포함하는 DTO 클래스
public class OrderStatusDto {
    private String status;
    private String displayName;

    public OrderStatusDto(String status, String displayName) {
        this.status = status;
        this.displayName = displayName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
