package com.example.DWShopProject.cart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart_product_snapshots")
public class CartProductSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private int price;
    private String description;

    @OneToOne(mappedBy = "cartProductSnapshot")
    private CartItem cartItem;
}
