package com.example.DWShopProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String productType;
    private String productName;
    private int price;
    private String explanation;
}