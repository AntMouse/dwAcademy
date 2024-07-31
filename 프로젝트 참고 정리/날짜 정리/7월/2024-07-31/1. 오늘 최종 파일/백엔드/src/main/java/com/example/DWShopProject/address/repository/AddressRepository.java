package com.example.DWShopProject.address.repository;

import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUser(User user);
}
