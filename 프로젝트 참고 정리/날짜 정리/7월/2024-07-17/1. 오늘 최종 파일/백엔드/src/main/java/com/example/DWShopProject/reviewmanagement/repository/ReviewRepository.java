package com.example.DWShopProject.reviewmanagement.repository;

import com.example.DWShopProject.reviewmanagement.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId); // 특정 상품에 대한 리뷰를 찾는 메서드
    Optional<Review> findByIdAndUserId(Long id, Long userId); // 사용자 검증용
}
