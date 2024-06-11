package com.example.DWShopProject.config;

import com.example.DWShopProject.entity.*;
import com.example.DWShopProject.repository.*;
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
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // 데이터 초기화
        productRepository.deleteAll();
        userRepository.deleteAll();
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

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            User user = new User();
            user.setUsertype("Customer");
            user.setUserId("user" + i);
            user.setUsername("User " + i);
            user.setPassword("password");
            user.setBirthdate("1990-01-0" + i);
            user.setGender(i % 2 == 0 ? "Male" : "Female");
            user.setEmail("user" + i + "@example.com");
            user.setContact("123-456-789" + i);
            user.setAddress("123 Street " + i + ", City, Country");
            users.add(user);
        }
        userRepository.saveAll(users);

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
