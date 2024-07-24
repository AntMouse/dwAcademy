package com.example.DWShopProject.cart.controller;

import com.example.DWShopProject.cart.dto.CartDto;
import com.example.DWShopProject.cart.entity.CartItem;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.cart.repository.CartItemRepository;
import com.example.DWShopProject.common.security.userdetails.UserDetailsImpl;
import com.example.DWShopProject.common.security.userdetails.UserDetailsServiceImpl;
import com.example.DWShopProject.cart.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    CartItemRepository cartItemRepository;

    // 장바구니 조회
    @GetMapping
    public ResponseEntity<?> getMyCart(HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CartItem> cartItems = cartService.getCartItemsByUserId(userDetails.getUser());

        return ResponseEntity.ok(cartItems);
    }

    // 장바구니에 아이템 추가
    @PostMapping("/items")
    public ResponseEntity<CartDto> addItemToCart(HttpServletRequest request, @RequestParam Long productId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CartDto cartDto = cartService.addItemToCart(userDetails.getUser(), productId);
        return ResponseEntity.ok(cartDto);
    }

    // 장바구니에서 아이템 제거
    @DeleteMapping("/items")
    public ResponseEntity<Void> removeItemFromCart(HttpServletRequest request, @RequestParam Long productId) {
        String token = jwtUtil.resolveToken(request);
        System.out.println("Token: " + token);

        if (token == null || !jwtUtil.validateToken(token)) {
            System.out.println("Invalid or missing token.");
            return ResponseEntity.status(401).build();
        }

        // 토큰에서 `userId`를 추출
        String userId = jwtUtil.getUserIdFromToken(token);

        try {
            // `userId`를 사용하여 `User` 엔티티를 조회하고, `id`를 가져옴
            User user = userDetailsService.loadUserEntityByUsername(userId);
            cartService.removeItemFromCart(user.getId(), productId);
            System.out.println("Item removed from cart.");
        } catch (Exception e) {
            System.out.println("Error removing item from cart: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok().build();
    }

    // 장바구니에서 특정 상품을 모두 제거
//    @DeleteMapping("/{id}/items/{productId}")
//    public ResponseEntity<CartDto> removeAllItemsFromCart(@PathVariable Long id, @PathVariable Long productId) {
//        CartDto cartDto = cartService.removeAllItemsFromCart(id, productId);
//        return ResponseEntity.ok(cartDto);
//    }
//
//    // 장바구니에서 아이템 수량 업데이트 (PUT 메서드 추가)
//    @PutMapping("/{id}/items/{productId}")
//    public ResponseEntity<CartDto> updateItemInCart(@PathVariable Long id, @PathVariable Long productId, @RequestParam int quantity) {
//        CartDto cartDto = cartService.updateItemInCart(id, productId, quantity);
//        return ResponseEntity.ok(cartDto);
//    }

    // 모든 장바구니 조회
//    @GetMapping
//    public ResponseEntity<List<CartDto>> getAllCarts() {
//        List<CartDto> cartDtos = cartService.getAllCarts();
//        return ResponseEntity.ok(cartDtos);
//    }

}
