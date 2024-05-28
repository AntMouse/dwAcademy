package com.example.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String productType;
    private String productName;
    private int price;
    private String explanation;
}