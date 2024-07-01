package com.example.DWTransferScoutProject.address.repository;

import com.example.DWTransferScoutProject.address.entity.Address;
import com.example.DWTransferScoutProject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
}
