package com.example.DWShopProject.productmanagement.dto;

import com.example.DWShopProject.productmanagement.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private Long productSubTypeId;
    private String productSubTypeName;
    private String productName;
    private int price;
    private String explanation;
    private String imageUrl;
    private LocalDateTime createDate;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.productSubTypeId = product.getProductSubType().getId();
        this.productSubTypeName = product.getProductSubType().getTypeName();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.explanation = product.getExplanation();
        this.imageUrl = product.getImageUrl();
        this.createDate = product.getCreateDate();
    }
}