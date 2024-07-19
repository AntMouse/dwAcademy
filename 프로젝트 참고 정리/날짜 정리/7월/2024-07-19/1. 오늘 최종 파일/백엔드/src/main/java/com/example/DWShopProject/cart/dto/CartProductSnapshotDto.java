package com.example.DWShopProject.cart.dto;

import com.example.DWShopProject.cart.entity.CartProductSnapshot;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductSnapshotDto {
    private Long id;
    private Long productId;
    private String productName;
    private int price;
    private String description;

    public CartProductSnapshotDto(CartProductSnapshot cartProductSnapshot) {
        this.id = cartProductSnapshot.getId();
        this.productId = cartProductSnapshot.getProductId();
        this.productName = cartProductSnapshot.getProductName();
        this.price = cartProductSnapshot.getPrice();
        this.description = cartProductSnapshot.getDescription();
    }
}
