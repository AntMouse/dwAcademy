package com.example.DWShopProject.searchmanagement.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchDto {
    private Long id;
    private ProductSubType productType;
    private String productName;
    private int price;
    private String explanation;
    private String imageUrl;
}
