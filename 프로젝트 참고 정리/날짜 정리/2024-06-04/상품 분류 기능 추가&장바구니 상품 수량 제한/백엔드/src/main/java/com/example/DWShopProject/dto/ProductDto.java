package com.example.DWShopProject.dto;

import com.example.DWShopProject.entity.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private ProductType productType;
    private String productName;
    private int price;
    private String explanation;
}
