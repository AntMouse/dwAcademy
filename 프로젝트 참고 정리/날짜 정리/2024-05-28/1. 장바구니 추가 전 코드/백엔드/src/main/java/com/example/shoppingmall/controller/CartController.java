package com.example.shoppingmall.controller;

import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCart(id));
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        return ResponseEntity.ok(cartService.createCart());
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long id, @RequestParam Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addItemToCart(id, productId, quantity));
    }

    @DeleteMapping("/{id}/items")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id, @RequestParam Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.removeItemFromCart(id, productId, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}