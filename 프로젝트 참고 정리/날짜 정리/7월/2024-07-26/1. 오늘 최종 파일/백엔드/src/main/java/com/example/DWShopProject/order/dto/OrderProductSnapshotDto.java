package com.example.DWShopProject.order.dto;

import com.example.DWShopProject.order.entity.OrderProductSnapshot;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductSnapshotDto {
    private Long id;
    private Long productId;
    private String productName;
    private int price;
    private String description;

    public OrderProductSnapshotDto(OrderProductSnapshot orderProductSnapshot) {
        this.id = orderProductSnapshot.getId();
        this.productId = orderProductSnapshot.getProductId();
        this.productName = orderProductSnapshot.getProductName();
        this.price = orderProductSnapshot.getPrice();
        this.description = orderProductSnapshot.getDescription();
    }
}
