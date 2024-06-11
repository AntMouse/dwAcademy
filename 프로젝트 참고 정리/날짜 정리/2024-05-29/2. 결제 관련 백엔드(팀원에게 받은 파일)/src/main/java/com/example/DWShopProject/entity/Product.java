package com.example.DWShopProject.entity;


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

    private String productType;
    private String productName;
    private int price;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private LocalDateTime createDate;

    @Builder
    public Product(String productType, String productName, int price, String explanation, LocalDateTime createDate) {
        this.productType = productType;
        this.productName = productName;
        this.price = price;
        this.explanation = explanation;
        this.createDate = createDate;
    }

    public void updateProduct(String productType, String productName, int price, String explanation) {
        this.productType = productType;
        this.productName = productName;
        this.price = price;
        this.explanation = explanation;
    }

    /*
    나중에 리뷰 기능 추가할 때 참고 코드

    import jakarta.persistence.CascadeType;
    import jakarta.persistence.OneToMany;

    위에 있는 import 2개 필요.

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Review> reviewList;
    */
}
