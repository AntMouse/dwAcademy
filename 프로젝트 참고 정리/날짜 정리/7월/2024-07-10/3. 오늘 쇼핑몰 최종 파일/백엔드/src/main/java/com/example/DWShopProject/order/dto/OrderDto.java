package com.example.DWShopProject.order.dto;

import com.example.DWShopProject.member.dto.MemberDto;
import com.example.DWShopProject.order.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class OrderDto {

    private Long id;
    private MemberDto member;
    private String recipientName;
    private String contactNumber;
    private String deliveryLocation;
    private LocalDateTime createDate;
    private String request;
    private int totalPrice;
    private OrderStatus status;
    private List<OrderItemDto> orderItems;


}
