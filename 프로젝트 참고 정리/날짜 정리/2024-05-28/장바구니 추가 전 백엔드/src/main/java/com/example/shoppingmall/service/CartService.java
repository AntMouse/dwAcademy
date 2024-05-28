package com.example.shoppingmall.service;

import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.entity.CartItem;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.CartRepository;
import com.example.shoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart createCart() {
        Cart newCart = new Cart();
        return cartRepository.save(newCart);
    }

    public Cart addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = getCart(cartId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long cartId, Long productId, int quantity) {
        Cart cart = getCart(cartId);

        CartItem item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        if (item.getQuantity() <= quantity) {
            cart.getItems().remove(item);
        } else {
            item.setQuantity(item.getQuantity() - quantity);
        }

        return cartRepository.save(cart);
    }

    public void deleteCart(Long cartId) {
        Cart cart = getCart(cartId);
        cartRepository.delete(cart);
    }
}