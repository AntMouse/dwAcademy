package com.example.DWShopProject.order.dto;

import com.example.DWShopProject.order.entity.Order;
import com.example.DWShopProject.account.dto.UserDto;
import com.example.DWShopProject.order.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private UserDto user;  // UserDto를 포함하여 유지보수성과 확장성을 고려
    private LocalDateTime orderDate;
    private String deliveryPostalCode;
    private String deliveryPrimaryAddress;
    private String deliveryDetailAddress;
    private String recipientName;
    private String recipientPhoneNumber;
    private String requestMessage;
    private OrderStatus status;
    private int totalPrice;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.user = new UserDto(order.getUser());  // User 엔티티를 UserDto로 변환
        this.orderDate = order.getOrderDate();
        this.deliveryPostalCode = order.getDeliveryPostalCode();
        this.deliveryPrimaryAddress = order.getDeliveryPrimaryAddress();
        this.deliveryDetailAddress = order.getDeliveryDetailAddress();
        this.recipientName = order.getRecipientName();
        this.recipientPhoneNumber = order.getRecipientPhoneNumber();
        this.requestMessage = order.getRequestMessage();
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        this.orderItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}