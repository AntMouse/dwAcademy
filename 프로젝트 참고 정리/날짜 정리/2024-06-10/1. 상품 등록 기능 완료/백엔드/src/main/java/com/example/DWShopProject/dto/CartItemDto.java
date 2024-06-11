package com.example.DWShopProject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItemDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private int quantity;
}
