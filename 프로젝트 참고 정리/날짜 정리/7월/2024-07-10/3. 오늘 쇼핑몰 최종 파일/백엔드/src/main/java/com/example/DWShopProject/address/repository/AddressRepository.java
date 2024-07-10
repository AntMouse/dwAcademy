package com.example.DWShopProject.address.repository;

import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByMember(Member member);
}
