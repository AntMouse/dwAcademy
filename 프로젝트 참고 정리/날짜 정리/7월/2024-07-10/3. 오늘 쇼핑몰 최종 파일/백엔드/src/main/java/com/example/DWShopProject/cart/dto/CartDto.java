package com.example.DWShopProject.cart.dto;

import com.example.DWShopProject.cart.entity.CartItem;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class CartDto {
    private Long id;
    private Long memberId;
    private List<CartItem> items;
}
