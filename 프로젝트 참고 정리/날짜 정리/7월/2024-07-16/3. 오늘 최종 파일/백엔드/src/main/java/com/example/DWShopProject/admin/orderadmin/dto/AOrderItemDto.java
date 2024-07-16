package com.example.DWShopProject.admin.orderadmin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AOrderItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private int price;
}