package com.example.DWShopProject.entity;

import com.example.DWShopProject.dto.OrderDto;
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
@Setter
@Builder
//주문내역 클래스
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    //고객이름, 배송정보, 연락처, 이메일
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private String recipientName; // 수취인 정보
    private String contactNumber; // 전화번호
    private String deliveryLocation; // 배송지
    private LocalDateTime createDate; //주문 생성날짜
    private String request; //요청사항
    private int totalPrice; //총 가격

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 상태 변경 메서드
    public void updateOrderInfo(String recipientName, String contactNumber, String deliveryLocation, String request, int totalPrice, OrderStatus status) {
        if (recipientName != null) this.recipientName = recipientName;
        if (contactNumber != null) this.contactNumber = contactNumber;
        if (deliveryLocation != null) this.deliveryLocation = deliveryLocation;
        if (request != null) this.request = request;
        if (totalPrice != 0) this.totalPrice = totalPrice;
        if (status != null) this.status = status;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
