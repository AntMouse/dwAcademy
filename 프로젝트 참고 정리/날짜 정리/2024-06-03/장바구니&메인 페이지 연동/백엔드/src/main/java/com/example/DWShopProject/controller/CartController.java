package com.example.DWShopProject.controller;

import com.example.DWShopProject.dto.CartDto;
import com.example.DWShopProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    // 장바구니 조회
    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long id) {
        CartDto cartDto = cartService.getCart(id);
        return ResponseEntity.ok(cartDto);
    }

    // 회원 ID로 장바구니 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<CartDto> getCartByMemberId(@PathVariable Long memberId) {
        CartDto cartDto = cartService.getCartByMemberId(memberId);
        return ResponseEntity.ok(cartDto);
    }

    // 장바구니 생성
    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestParam Long memberId) {
        CartDto cartDto = cartService.createCart(memberId);
        return ResponseEntity.ok(cartDto);
    }

    // 장바구니에 아이템 추가
    @PostMapping("/{id}/items")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long id, @RequestParam Long productId, @RequestParam int quantity) {
        CartDto cartDto = cartService.addItemToCart(id, productId, quantity);
        return ResponseEntity.ok(cartDto);
    }

    // 장바구니에서 아이템 제거
    @DeleteMapping("/{id}/items")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable Long id, @RequestParam Long productId, @RequestParam int quantity) {
        CartDto cartDto = cartService.removeItemFromCart(id, productId, quantity);
        return ResponseEntity.ok(cartDto);
    }

    // 모든 장바구니 조회
    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        List<CartDto> cartDtos = cartService.getAllCarts();
        return ResponseEntity.ok(cartDtos);
    }
}
