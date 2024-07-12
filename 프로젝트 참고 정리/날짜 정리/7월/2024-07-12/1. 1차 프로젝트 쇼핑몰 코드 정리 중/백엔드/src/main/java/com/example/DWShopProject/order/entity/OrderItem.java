package com.example.DWShopProject.order.entity;

import com.example.DWShopProject.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    public void updateOrderItemInfo(int quantity, int price) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void increaseQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity += quantity;
        }
    }

    public void decreaseQuantity(int quantity) {
        if (quantity > 0) {
            if (this.quantity >= quantity) {
                this.quantity -= quantity;
            } else {
                this.quantity = 0;
            }
        }
    }
}
