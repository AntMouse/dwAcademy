package com.example.DWShopProject.cart.dto;

import com.example.DWShopProject.cart.entity.CartItem;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {
    private Long cartId;
    private CartProductSnapshotDTO cartProductSnapshot;
    private int quantity;
}