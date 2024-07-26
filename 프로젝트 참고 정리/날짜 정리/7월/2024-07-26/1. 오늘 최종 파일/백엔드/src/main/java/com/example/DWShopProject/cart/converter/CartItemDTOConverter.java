package com.example.DWShopProject.cart.converter;

import com.example.DWShopProject.cart.dto.CartItemDTO;
import com.example.DWShopProject.cart.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemDTOConverter {

    private final CartProductSnapshotDTOConverter cartProductSnapshotDtoConverter;

    public CartItemDTO convertToCartItemDto(CartItem cartItem) {
        return CartItemDTO.builder()
                .cartId(cartItem.getCart().getId())
                .cartProductSnapshot(cartProductSnapshotDtoConverter.convertToCartProductSnapshotDto(cartItem.getCartProductSnapshot()))
                .quantity(cartItem.getQuantity())
                .build();
    }

    public CartItem convertToCartItemEntity(CartItemDTO cartItemDto) {
        return CartItem.builder()
                .cart(null) // 실제 Cart entity 설정은 필요에 따라 추가합니다.
                .cartProductSnapshot(cartProductSnapshotDtoConverter.convertToCartProductSnapshotEntity(cartItemDto.getCartProductSnapshot()))
                .quantity(cartItemDto.getQuantity())
                .build();
    }
}
