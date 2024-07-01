package com.example.DWShopProject.entity;

import com.example.DWShopProject.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productType;

    private String productName;
    private int price;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private String productImgUrl; // 예: "products/TOPS/HOODIES/image1.jpg"

    private LocalDateTime createDate;

    public void updateProductInfo(ProductTypeEnum productType, String productName, Integer price, String explanation, String productImgUrl) {
        if (productType != null) this.productType = productType;
        if (productName != null) this.productName = productName;
        if (price != null) this.price = price;
        if (explanation != null) this.explanation = explanation;
        if (productImgUrl != null) this.productImgUrl = productImgUrl;
    }

    public String getProductImgUrl() {
        return productImgUrl.toLowerCase(); // 소문자로 변환하여 URL로 반환
    }
}
