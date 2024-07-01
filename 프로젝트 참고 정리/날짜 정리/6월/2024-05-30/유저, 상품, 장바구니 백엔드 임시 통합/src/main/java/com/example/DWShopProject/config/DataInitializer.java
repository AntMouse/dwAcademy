package com.example.DWShopProject.config;

import com.example.DWShopProject.entity.*;
import com.example.DWShopProject.repository.*;
import com.example.DWShopProject.security.MemberRoleEnum;
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

        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Member member = new Member();
            member.setMemberType(MemberRoleEnum.USER); // 반드시 설정
            member.setMemberId("user" + i);
            member.setMemberName("User " + i);
            member.setPassword("password");
            member.setBirthdate("1990-01-0" + i);
            member.setGender(i % 2 == 0 ? "Male" : "Female");
            member.setEmail("user" + i + "@example.com");
            member.setContact("123-456-789" + i);
            member.setAddress("123 Street " + i + ", City, Country");
            members.add(member);
        }
        memberRepository.saveAll(members);

        List<Cart> carts = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            Cart cart = new Cart();
            cart.setName("Cart " + i);
            carts.add(cart);
        }
        cartRepository.saveAll(carts);

        List<CartItem> cartItems = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            for (int j = 0; j < products.size(); j++) {
                if (j % (i + 1) == 0) {
                    CartItem cartItem = new CartItem();
                    cartItem.setCart(cart);
                    cartItem.setProduct(products.get(j));
                    cartItem.setQuantity(1 + (j % 3));
                    cartItems.add(cartItem);
                }
            }
        }
        cartItemRepository.saveAll(cartItems);
    }
}
