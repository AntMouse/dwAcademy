package com.example.DWShopProject.order.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "snapshot_id", referencedColumnName = "id")
    private OrderProductSnapshot orderProductSnapshot;

    @Column(nullable = false)
    private int quantity;

    public void assignOrderIfValid(Order order) {
        if (this.order == null) {
            this.order = order;
        }
    }

    public int getPrice() {
        return orderProductSnapshot.getPrice();
    }
}
