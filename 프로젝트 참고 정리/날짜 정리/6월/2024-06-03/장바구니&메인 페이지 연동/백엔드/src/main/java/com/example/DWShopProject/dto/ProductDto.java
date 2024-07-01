package com.example.DWShopProject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private String productType;
    private String productName;
    private int price;
    private String explanation;
}
