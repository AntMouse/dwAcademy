package com.example.DWShopProject.cart.dto;

import com.example.DWShopProject.cart.entity.CartItem;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
    private Long id;
    private Long cartId;
    private CartProductSnapshotDto cartProductSnapshot;
    private int quantity;
    private int price;

    public CartItemDto(CartItem cartItem) {
        this.id = cartItem.getId();
        this.cartId = cartItem.getCart().getId();
        this.cartProductSnapshot = new CartProductSnapshotDto(cartItem.getCartProductSnapshot());
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
    }
}