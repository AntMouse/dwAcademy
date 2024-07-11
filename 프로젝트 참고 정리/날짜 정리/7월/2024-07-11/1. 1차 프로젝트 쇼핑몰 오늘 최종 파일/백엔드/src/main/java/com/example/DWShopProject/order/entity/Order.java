package com.example.DWShopProject.order.entity;

import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
//주문내역 클래스
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    //고객이름,  연락처, 이메일
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private String recipientName; // 수취인 정보
    private String contactNumber; // 전화번호
    private String deliveryLocation; // 배송지
    //주문 생성날짜
    private LocalDateTime createDate;
    //요청사항
    private String request;
    //총 가격
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.assignOrder(this);
    }

    // 주문 항목 제거 메서드
    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.assignOrder(null);
    }

    public void updateOrderInfo(String recipientName, String contactNumber, String deliveryLocation, String request, int totalPrice, OrderStatus status) {
        if (recipientName != null) this.recipientName = recipientName;
        if (contactNumber != null) this.contactNumber = contactNumber;
        if (deliveryLocation != null) this.deliveryLocation = deliveryLocation;
        if (request != null) this.request = request;
        if (totalPrice >= 0) this.totalPrice = totalPrice;
        if (status != null) this.status = status;
    }



}
