package com.example.DWShopProject.dto;

import com.example.DWShopProject.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderDto {
    private Long id;
    private Long memberId;
    private String recipientName;
    private String contactNumber;
    private String deliveryLocation;
    private LocalDateTime createDate;
    private String request;
    private int totalPrice;
    private OrderStatus status;
    private List<OrderItemDto> orderItems;
}