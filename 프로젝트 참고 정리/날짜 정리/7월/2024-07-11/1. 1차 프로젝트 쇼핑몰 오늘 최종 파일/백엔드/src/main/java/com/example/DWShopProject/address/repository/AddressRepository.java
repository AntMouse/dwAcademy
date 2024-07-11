package com.example.DWShopProject.address.repository;

import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
}
