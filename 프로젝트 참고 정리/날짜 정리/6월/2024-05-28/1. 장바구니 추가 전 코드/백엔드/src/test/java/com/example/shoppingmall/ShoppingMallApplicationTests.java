package com.example.shoppingmall;

import com.example.shoppingmall.service.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingMallApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
    }

}