package com.example.DWShopProject.dto;

import com.example.DWShopProject.enums.ProductTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchResultDto {
    private Long id; // 상품 ID
    private ProductTypeEnum productType; // 상품 유형
    private String productName; // 상품명
    private int price; // 가격
    private String explanation; // 설명
    private String imageUrl; // 이미지 URL
}
