package com.example.DWShopProject.temporaryorder.repository;

import com.example.DWShopProject.temporaryorder.entity.TemporaryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporaryOrderRepository extends JpaRepository<TemporaryOrder,Long> {
//    Optional<TemporaryOrder> findByIdAndMemberId(Long id);
    void deleteById(Long id);

}
