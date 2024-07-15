package com.example.DWShopProject.order.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_product_snapshots")
public class OrderProductSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String name;
    private int price;
    private String description;

    @OneToOne(mappedBy = "orderProductSnapshot")
    private OrderItem orderItem;
}
