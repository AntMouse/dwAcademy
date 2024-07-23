package com.example.DWShopProject.cart.repository;

import com.example.DWShopProject.cart.entity.Cart;
import com.example.DWShopProject.cart.entity.CartItem;
import com.example.DWShopProject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    void deleteByCartIdAndProductId(Long cartId, Long productId);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}
