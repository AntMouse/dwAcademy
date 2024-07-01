package com.example.DWShopProject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private int price;
}