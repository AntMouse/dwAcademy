package com.example.DWShopProject.cart.converter;

import com.example.DWShopProject.cart.dto.CartDTO;
import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.user.converter.UserDTOConverter;
import com.example.DWShopProject.user.dto.UserDTO;
import com.example.DWShopProject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartDTOConverter {

    private final UserDTOConverter userDtoConverter;
    private final CartItemDTOConverter cartItemDtoConverter;

    public CartDTO convertToCartDto(Cart cart) {
        UserDTO userDto = userDtoConverter.convertToUserDto(cart.getUser());
        return CartDTO.builder()
                .user(userDto)
                .cartItems(cart.getCartItems().stream()
                        .map(cartItemDtoConverter::convertToCartItemDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public Cart convertToCartEntity(CartDTO cartDto) {
        return Cart.builder()
                .user(userDtoConverter.convertToUserEntity(cartDto.getUser()))
                .cartItems(cartDto.getCartItems().stream()
                        .map(cartItemDtoConverter::convertToCartItemEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
