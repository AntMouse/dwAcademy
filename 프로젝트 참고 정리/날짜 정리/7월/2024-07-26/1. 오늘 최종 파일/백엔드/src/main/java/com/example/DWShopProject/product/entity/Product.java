package com.example.DWShopProject.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_sub_type_id", referencedColumnName = "id", nullable = false)
    private ProductSubType productSubType;

    private String productName;
    private int price;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private String imageUrl;
    private LocalDateTime createDate;

    public void updateProductInfo(ProductSubType productSubType, String productName, Integer price, String explanation, String imageUrl) {
        if (productSubType != null) {
            this.productSubType = productSubType;
        }
        if (productName != null && !productName.isEmpty()) {
            this.productName = productName;
        }
        if (price != null) {
            this.price = price;
        }
        if (explanation != null && !explanation.isEmpty()) {
            this.explanation = explanation;
        }
        if (imageUrl != null && !imageUrl.isEmpty()) {
            this.imageUrl = imageUrl;
        }
    }
}
