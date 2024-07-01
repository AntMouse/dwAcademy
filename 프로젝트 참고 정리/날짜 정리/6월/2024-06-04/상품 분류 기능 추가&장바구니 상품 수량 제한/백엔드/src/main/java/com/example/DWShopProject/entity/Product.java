package com.example.DWShopProject.entity;

import com.example.DWShopProject.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
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

    private LocalDateTime createDate;

    @Builder
    public Product(ProductTypeEnum productType, String productName, int price, String explanation, LocalDateTime createDate) {
        this.productType = productType;
        this.productName = productName;
        this.price = price;
        this.explanation = explanation;
        this.createDate = createDate;
    }

    public void updateProductInfo(ProductTypeEnum productType, String productName, Integer price, String explanation) {
        if (productType != null) this.productType = productType;
        if (productName != null) this.productName = productName;
        if (price != null) this.price = price;
        if (explanation != null) this.explanation = explanation;
    }
}

