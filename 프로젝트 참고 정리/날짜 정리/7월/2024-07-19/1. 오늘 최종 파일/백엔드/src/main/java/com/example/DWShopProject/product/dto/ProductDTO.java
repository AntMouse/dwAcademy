package com.example.DWShopProject.product.dto;

import com.example.DWShopProject.product.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private Long productSubTypeId;
    private String productName;
    private int price;
    private String explanation;
    private String imageUrl;
    private LocalDateTime createDate;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.productSubTypeId = product.getProductSubType().getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.explanation = product.getExplanation();
        this.imageUrl = product.getImageUrl();
        this.createDate = product.getCreateDate();
    }
}