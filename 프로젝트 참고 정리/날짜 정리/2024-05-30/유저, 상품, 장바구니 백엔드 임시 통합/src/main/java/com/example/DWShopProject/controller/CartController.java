package com.example.DWShopProject.controller;

import com.example.DWShopProject.entity.Cart;
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

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCart(id));
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestParam String name) {
        return ResponseEntity.ok(cartService.createCart(name));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long id, @RequestParam Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addItemToCart(id, productId, quantity));
    }

    @DeleteMapping("/{id}/items")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id, @RequestParam Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.removeItemFromCart(id, productId, quantity));
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Cart> getCartByName(@PathVariable String name) {
        return ResponseEntity.ok(cartService.getCartByName(name));
    }
}
