package com.example.DWShopProject.dto;

import com.example.DWShopProject.entity.CartItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CartDto {
    private Long id;
    private Long memberId;
    private List<CartItem> items;
}
