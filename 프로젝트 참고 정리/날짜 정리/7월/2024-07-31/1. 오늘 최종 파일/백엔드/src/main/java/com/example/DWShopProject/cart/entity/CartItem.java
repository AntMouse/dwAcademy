package com.example.DWShopProject.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_product_snapshot_id", referencedColumnName = "id")
    private CartProductSnapshot cartProductSnapshot;

    @Column(nullable = false)
    private int quantity;

    public void assignCart(Cart cart) {
        if (this.cart == null) {
            this.cart = cart;
        }
    }

    public void removeCart() {
        if (this.cart != null) {
            this.cart = null;
        }
    }

    public void updateQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
}
