package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId); // 특정 상품에 대한 리뷰를 찾는 메서드
}
