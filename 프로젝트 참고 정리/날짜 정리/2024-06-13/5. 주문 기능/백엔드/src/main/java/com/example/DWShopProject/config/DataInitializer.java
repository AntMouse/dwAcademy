package com.example.DWShopProject.config;

import com.example.DWShopProject.dto.MemberDto;
import com.example.DWShopProject.entity.*;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.*;
import com.example.DWShopProject.security.MemberRoleEnum;
import com.example.DWShopProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private ProductTypeMgmtRepository productTypeMgmtRepository;

    @Override
    public void run(String... args) throws Exception {
        // 데이터 초기화
        productRepository.deleteAll();
        memberRepository.deleteAll();
        cartRepository.deleteAll();
        cartItemRepository.deleteAll();

        // ProductType 저장
        List<ProductTypeMgmt> productTypeMgms = new ArrayList<>();
        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            ProductTypeMgmt productTypeMgmt = new ProductTypeMgmt(type, type.getParentTypeEnum());
            productTypeMgms.add(productTypeMgmt);
        }
        productTypeMgmtRepository.saveAll(productTypeMgms);

        // 기본 데이터 생성
        List<Product> products = new ArrayList<>();
        String githubBaseUrl = "https://raw.githubusercontent.com/AntMouse/dwAcademy/main/ProjectData/dw_shop_app/images/products";

        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            String parentTypePath = type.getParentTypeEnum().name().toLowerCase();
            String typePath = type.name().toLowerCase();
            for (int i = 1; i <= 3; i++) {
                String displayName = type.getDisplayName().toLowerCase().replace(" ", "_");
                String imageName = parentTypePath + "/" + typePath + "/" + displayName + "_" + i + ".jpg";
                String imageUrl = githubBaseUrl + "/" + imageName;

                Product product = Product.builder()
                        .productType(type)
                        .productName(type.getDisplayName() + " - Product " + i)
                        .price(100 * i)
                        .explanation("설명 " + type.getDisplayName() + " - Product " + i)
                        .imageUrl(imageUrl)
                        .createDate(LocalDateTime.now())
                        .build();
                products.add(product);
            }
        }
        productRepository.saveAll(products);

        // 외부 파일에서 데이터 읽기
        List<String> names = readLinesFromFile("src/main/resources/data/names.txt");
        List<String> emails = readLinesFromFile("src/main/resources/data/emails.txt");
        List<String> phoneNumbers = readLinesFromFile("src/main/resources/data/phoneNumbers.txt");
        List<String> addresses = readLinesFromFile("src/main/resources/data/addresses.txt");

        // 회원 및 장바구니 생성
        for (int i = 0; i < 120; i++) {
            MemberDto memberDto = MemberDto.builder()
                    .memberType(MemberRoleEnum.ROLE_USER)
                    .memberId(emails.get(i % emails.size()))
                    .memberName(names.get(i % names.size()))
                    .password("1234")
                    .birthdate(String.format("1990-%02d-%02d", (i % 12) + 1, (i % 28) + 1))
                    .gender(i % 2 == 0 ? "Male" : "Female")
                    .email(emails.get(i % emails.size()) + "@example.com")
                    .contact(phoneNumbers.get(i % phoneNumbers.size()))
                    .address(addresses.get(i % addresses.size()))
                    .build();

            memberService.signUp(memberDto);
        }

        List<Cart> carts = cartRepository.findAll();

        List<CartItem> cartItems = new ArrayList<>();
        int productIndex = 0;
        for (Cart cart : carts) {
            List<Product> shuffledProducts = new ArrayList<>(products);
            Collections.shuffle(shuffledProducts);
            for (int j = 0; j < 5; j++) {
                Product product = shuffledProducts.get(j);
                CartItem cartItem = CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .quantity(1)
                        .build();
                cartItems.add(cartItem);
            }
        }
        cartItemRepository.saveAll(cartItems);
    }

    private List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
