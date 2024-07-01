package com.example.DWShopProject.config;

import com.example.DWShopProject.dto.MemberDto;
import com.example.DWShopProject.entity.*;
import com.example.DWShopProject.repository.*;
import com.example.DWShopProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public void run(String... args) throws Exception {
        // 데이터 초기화
        productRepository.deleteAll();
        memberRepository.deleteAll();
        cartRepository.deleteAll();
        cartItemRepository.deleteAll();

        // 기본 데이터 생성
        List<Product> products = new ArrayList<>();
        String[] types = {"Type A", "Type B", "Type C", "Type D", "Type E"};
        for (String type : types) {
            for (int i = 1; i <= 3; i++) {
                Product product = Product.builder()
                        .productType(type)
                        .productName(type + " - Product " + i)
                        .price(100 * i)
                        .explanation("설명 " + type + " - Product " + i)
                        .createDate(LocalDateTime.now())
                        .build();
                products.add(product);
            }
        }
        productRepository.saveAll(products);

        // 회원 및 장바구니 생성
        for (int i = 1; i <= 7; i++) {
            MemberDto memberDto = MemberDto.builder()
                    .memberType("USER") // 반드시 설정
                    .memberId("user" + i)
                    .memberName("User " + i)
                    .password("1234") // 비밀번호 설정
                    .birthdate("1990-01-0" + i)
                    .gender(i % 2 == 0 ? "Male" : "Female")
                    .email("user" + i + "@example.com")
                    .contact("123-456-789" + i)
                    .address("123 Street " + i + ", City, Country")
                    .build();

            memberService.signUp(memberDto);
        }

        List<Cart> carts = cartRepository.findAll();

        List<CartItem> cartItems = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            for (int j = 0; j < products.size(); j++) {
                if (j % (i + 1) == 0) {
                    CartItem cartItem = CartItem.builder()
                            .cart(cart)
                            .product(products.get(j))
                            .quantity(1 + (j % 3))
                            .build();
                    cartItems.add(cartItem);
                }
            }
        }
        cartItemRepository.saveAll(cartItems);
    }
}
