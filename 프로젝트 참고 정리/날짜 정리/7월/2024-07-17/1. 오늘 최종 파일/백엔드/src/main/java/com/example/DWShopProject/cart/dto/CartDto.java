package com.example.DWShopProject.cart.dto;

import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.account.dto.UserDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private UserDto user;
    private List<CartItemDto> cartItems;

    public CartDto(Cart cart) {
        this.id = cart.getId();
        this.user = new UserDto(cart.getUser());
        this.cartItems = cart.getCartItems().stream().map(CartItemDto::new).collect(Collectors.toList());
    }
}
