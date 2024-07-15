package com.example.DWShopProject.product.dto;

import com.example.DWShopProject.product.enums.ProductSubType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDto {
    private Long id;
    private ProductSubType productType;
    private String productName;
    private int price;
    private String explanation;
    private String imageUrl;
}