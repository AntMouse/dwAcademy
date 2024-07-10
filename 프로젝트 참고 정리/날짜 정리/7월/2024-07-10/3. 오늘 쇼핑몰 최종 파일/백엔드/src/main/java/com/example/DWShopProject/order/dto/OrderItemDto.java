package com.example.DWShopProject.order.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long productId;
    private String productName;
    private String imageUrl;
    private int quantity;
    private int price;

}
