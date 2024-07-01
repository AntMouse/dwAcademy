package com.example.DWShopProject.service;

import com.example.DWShopProject.dto.CartDto;
import com.example.DWShopProject.dto.CartItemDto;
import com.example.DWShopProject.entity.Cart;
import com.example.DWShopProject.entity.CartItem;
import com.example.DWShopProject.entity.Member;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.repository.CartRepository;
import com.example.DWShopProject.repository.MemberRepository;
import com.example.DWShopProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, MemberRepository memberRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    public CartDto getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));
        return mapToDto(cart);
    }

    public CartDto getCartByMemberId(Long memberId) {
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));
        return mapToDto(cart);
    }

    public CartDto createCart(Long memberId) {
        Cart newCart = Cart.builder()
                .member(memberRepository.findById(memberId)
                        .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다.")))
                .items(new ArrayList<>())
                .build();
        return mapToDto(cartRepository.save(newCart));
    }

    public CartDto addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.updateQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cart.addItem(newItem);
        }

        return mapToDto(cartRepository.save(cart));
    }

    public CartDto removeItemFromCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));

        CartItem item = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("장바구니에 해당 상품이 없습니다."));

        if (item.getQuantity() <= quantity) {
            cart.removeItem(item);
        } else {
            item.updateQuantity(item.getQuantity() - quantity);
        }

        return mapToDto(cartRepository.save(cart));
    }

    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CartDto mapToDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .memberId(cart.getMember().getId())
                .items(cart.getItems())
                .build();
    }

    private CartItemDto mapToItemDto(CartItem cartItem) {
        return CartItemDto.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
