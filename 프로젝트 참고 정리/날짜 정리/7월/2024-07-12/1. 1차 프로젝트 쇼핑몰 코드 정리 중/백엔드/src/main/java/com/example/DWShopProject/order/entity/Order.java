package com.example.DWShopProject.order.entity;

import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String deliveryPostalCode;

    @Column(nullable = false)
    private String deliveryPrimaryAddress;

    @Column(nullable = false)
    private String deliveryDetailAddress;

    private String requestMessage;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private int totalPrice;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(User user, LocalDateTime orderDate, String deliveryPostalCode, String deliveryPrimaryAddress,
                 String deliveryDetailAddress, String requestMessage, OrderStatus status) {
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryPostalCode = deliveryPostalCode;
        this.deliveryPrimaryAddress = deliveryPrimaryAddress;
        this.deliveryDetailAddress = deliveryDetailAddress;
        this.requestMessage = requestMessage;
        this.status = status;
        recalculateTotalPrice();
    }

    public void updateOrderDetails(String deliveryPostalCode, String deliveryPrimaryAddress,
                                   String deliveryDetailAddress, String requestMessage, OrderStatus status) {
        if (deliveryPostalCode != null && !deliveryPostalCode.isEmpty()) {
            this.deliveryPostalCode = deliveryPostalCode;
        }
        if (deliveryPrimaryAddress != null && !deliveryPrimaryAddress.isEmpty()) {
            this.deliveryPrimaryAddress = deliveryPrimaryAddress;
        }
        if (deliveryDetailAddress != null && !deliveryDetailAddress.isEmpty()) {
            this.deliveryDetailAddress = deliveryDetailAddress;
        }
        if (requestMessage != null) {
            this.requestMessage = requestMessage;
        }
        if (status != null) {
            this.status = status;
        }
    }

    // 주문 아이템 추가 메서드
    public void addOrderItem(OrderItem orderItem) {
        for (OrderItem item : orderItems) {
            if (item.getProduct().equals(orderItem.getProduct())) {
                item.increaseQuantity(orderItem.getQuantity());
                recalculateTotalPrice();
                return;
            }
        }
        orderItems.add(orderItem);
        orderItem.setOrder(this);
        recalculateTotalPrice();
    }

    // 주문 아이템 제거 메서드
    public void removeOrderItem(OrderItem orderItem) {
        for (OrderItem item : orderItems) {
            if (item.getProduct().equals(orderItem.getProduct())) {
                if (item.getQuantity() > orderItem.getQuantity()) {
                    item.decreaseQuantity(orderItem.getQuantity());
                } else {
                    orderItems.remove(item);
                    item.setOrder(null);
                }
                recalculateTotalPrice();
                return;
            }
        }
    }

    // 총 가격 재계산 메서드
    public void recalculateTotalPrice() {
        this.totalPrice = orderItems.stream().mapToInt(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
