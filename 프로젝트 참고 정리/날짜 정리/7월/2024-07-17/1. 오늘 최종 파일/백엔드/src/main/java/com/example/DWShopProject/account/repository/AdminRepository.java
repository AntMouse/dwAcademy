package com.example.DWShopProject.account.repository;

import com.example.DWShopProject.account.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}