package com.example.DWShopProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity; //구매할 아이템 갯수

    private int price; // 주문할 당시의 가격(변동x)

    // 상태 변경 메서드
    public void updateOrderItemInfo(Order order, Product product, int quantity, int price) {
        if (order != null) this.order = order;
        if (product != null) this.product = product;
        if (quantity != 0) this.quantity = quantity;
        if (price != 0) this.price = price;
    }
}
