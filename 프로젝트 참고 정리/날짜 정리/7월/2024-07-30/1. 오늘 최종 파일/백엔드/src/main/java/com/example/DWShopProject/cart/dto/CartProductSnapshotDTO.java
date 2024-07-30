package com.example.DWShopProject.cart.dto;

import com.example.DWShopProject.cart.entity.CartProductSnapshot;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductSnapshotDTO {
    private Long productId;
    private String productName;
    private int price;
    private String description;
}
