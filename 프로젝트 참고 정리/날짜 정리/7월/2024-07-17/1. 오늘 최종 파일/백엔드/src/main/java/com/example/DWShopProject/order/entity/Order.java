package com.example.DWShopProject.order.entity;

import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(nullable = false)
    private String recipientName;

    @Column(nullable = false)
    private String recipientPhoneNumber;

    private String requestMessage;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    private int totalPrice;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(User user, LocalDateTime orderDate, String deliveryPostalCode, String deliveryPrimaryAddress,
                 String deliveryDetailAddress, String recipientName, String recipientPhoneNumber, String requestMessage, OrderStatus status) {
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryPostalCode = deliveryPostalCode;
        this.deliveryPrimaryAddress = deliveryPrimaryAddress;
        this.deliveryDetailAddress = deliveryDetailAddress;
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.requestMessage = requestMessage;
        this.status = status;
        this.totalPrice = 0;
    }

    public void updateOrderDetails(String deliveryPostalCode, String deliveryPrimaryAddress,
                                   String deliveryDetailAddress, String recipientName, String recipientPhoneNumber, String requestMessage, OrderStatus status) {
        if (deliveryPostalCode != null && !deliveryPostalCode.isEmpty()) {
            this.deliveryPostalCode = deliveryPostalCode;
        }
        if (deliveryPrimaryAddress != null && !deliveryPrimaryAddress.isEmpty()) {
            this.deliveryPrimaryAddress = deliveryPrimaryAddress;
        }
        if (deliveryDetailAddress != null && !deliveryDetailAddress.isEmpty()) {
            this.deliveryDetailAddress = deliveryDetailAddress;
        }
        if (recipientName != null && !recipientName.isEmpty()) {
            this.recipientName = recipientName;
        }
        if (recipientPhoneNumber != null && !recipientPhoneNumber.isEmpty()) {
            this.recipientPhoneNumber = recipientPhoneNumber;
        }
        if (requestMessage != null) {
            this.requestMessage = requestMessage;
        }
        if (status != null) {
            this.status = status;
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.assignOrder(this);
    }

    public void updateTotalPrice(int newTotalPrice) {
        if (newTotalPrice >= 0) {
            this.totalPrice = newTotalPrice;
        }
    }
}
