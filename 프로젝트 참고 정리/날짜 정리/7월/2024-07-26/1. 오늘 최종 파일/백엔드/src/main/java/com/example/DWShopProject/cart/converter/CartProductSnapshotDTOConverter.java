package com.example.DWShopProject.cart.converter;

import com.example.DWShopProject.cart.dto.CartProductSnapshotDTO;
import com.example.DWShopProject.cart.entity.CartProductSnapshot;
import org.springframework.stereotype.Component;

@Component
public class CartProductSnapshotDTOConverter {

    public CartProductSnapshotDTO convertToCartProductSnapshotDto(CartProductSnapshot cartProductSnapshot) {
        return CartProductSnapshotDTO.builder()
                .productId(cartProductSnapshot.getProductId())
                .productName(cartProductSnapshot.getProductName())
                .price(cartProductSnapshot.getPrice())
                .description(cartProductSnapshot.getDescription())
                .build();
    }

    public CartProductSnapshot convertToCartProductSnapshotEntity(CartProductSnapshotDTO cartProductSnapshotDto) {
        return CartProductSnapshot.builder()
                .productId(cartProductSnapshotDto.getProductId())
                .productName(cartProductSnapshotDto.getProductName())
                .price(cartProductSnapshotDto.getPrice())
                .description(cartProductSnapshotDto.getDescription())
                .build();
    }
}
