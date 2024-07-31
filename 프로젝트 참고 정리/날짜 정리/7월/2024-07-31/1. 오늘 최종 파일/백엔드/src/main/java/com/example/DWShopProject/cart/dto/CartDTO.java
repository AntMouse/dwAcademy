package com.example.DWShopProject.cart.dto;

import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.user.dto.UserDTO;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private UserDTO user;
    private List<CartItemDTO> cartItems;
}
