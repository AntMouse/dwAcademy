package com.example.DWShopProject.order.dto;

import com.example.DWShopProject.order.entity.OrderItem;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private OrderProductSnapshotDto orderProductSnapshot;
    private int quantity;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.orderId = orderItem.getOrder().getId();
        this.orderProductSnapshot = new OrderProductSnapshotDto(orderItem.getOrderProductSnapshot());
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}