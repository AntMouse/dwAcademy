package com.example.DWShopProject.dto;

import com.example.DWShopProject.enums.ProductTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {
    private Long id;
    private ProductTypeEnum productType;
    private String productName;
    private double price;
    private String explanation;
    private String imageUrl;
}
