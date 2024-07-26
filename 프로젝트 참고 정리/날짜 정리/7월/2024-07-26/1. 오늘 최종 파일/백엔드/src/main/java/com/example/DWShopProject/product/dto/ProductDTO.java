package com.example.DWShopProject.product.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long productSubTypeId;
    private String productName;
    private int price;
    private String explanation;
    private String imageUrl;
    private LocalDateTime createDate;
}