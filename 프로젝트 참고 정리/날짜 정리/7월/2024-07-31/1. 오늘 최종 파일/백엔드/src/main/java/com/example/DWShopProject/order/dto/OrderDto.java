package com.example.DWShopProject.order.dto;

import com.example.DWShopProject.order.entity.Order;
import com.example.DWShopProject.user.dto.UserDTO;
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
    private UserDTO user;  // UserDto를 포함하여 유지보수성과 확장성을 고려
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
}