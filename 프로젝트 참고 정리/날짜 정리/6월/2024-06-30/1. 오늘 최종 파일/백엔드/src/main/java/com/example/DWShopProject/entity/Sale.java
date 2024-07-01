package com.example.DWShopProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate; // 매출 생성 날짜
    private Long productId; // 상품 ID
    private int price; // 상품 가격
    private int quantity; // 상품 수량

    public Sale(LocalDateTime createDate, Long productId, int price, int quantity) {
        this.createDate = createDate;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }
}
