package com.example.DWShopProject.entity;

import com.example.DWShopProject.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter

// 주문내역 클래스
@Table(name = "orders") // 테이블 이름을 orders로 변경
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //고객이름, 배송정보, 연락처, 이메일

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>(); // @Builder.Default 추가

    private String recipientName; // 수취인 정보
    private String contactNumber; // 전화번호
    private String deliveryLocation; // 배송지
    private LocalDateTime createDate; //주문 생성날짜
    private String request; //요청사항
    private int totalPrice; //총 가격

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태

    // 상태 변경 메서드
    public void updateOrderInfo(String recipientName, String contactNumber, String deliveryLocation, String request, int totalPrice, OrderStatus status) {
        if (recipientName != null) this.recipientName = recipientName;
        if (contactNumber != null) this.contactNumber = contactNumber;
        if (deliveryLocation != null) this.deliveryLocation = deliveryLocation;
        if (request != null) this.request = request;
        if (totalPrice >= 0) this.totalPrice = totalPrice;
        if (status != null) this.status = status;
    }

    // 주문 항목 추가 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.assignOrder(this);
    }

    // 주문 항목 제거 메서드
    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.assignOrder(null);
    }
}