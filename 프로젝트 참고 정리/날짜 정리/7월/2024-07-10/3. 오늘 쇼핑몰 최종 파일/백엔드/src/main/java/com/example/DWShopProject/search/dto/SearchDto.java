package com.example.DWShopProject.search.dto;

import com.example.DWShopProject.product.enums.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchDto {
    private Long id;
    private ProductType productType;
    private String productName;
    private int price;
    private String explanation;
    private String imageUrl;
}
