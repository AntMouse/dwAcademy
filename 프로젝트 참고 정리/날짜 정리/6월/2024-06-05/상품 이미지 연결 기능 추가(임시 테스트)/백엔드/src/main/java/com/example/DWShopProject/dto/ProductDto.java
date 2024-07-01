package com.example.DWShopProject.dto;

import com.example.DWShopProject.enums.ProductTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private ProductTypeEnum productType;
    private String productName;
    private int price;
    private String explanation;
    private String productImgUrl; // 이미지 URL 필드 추가
}
