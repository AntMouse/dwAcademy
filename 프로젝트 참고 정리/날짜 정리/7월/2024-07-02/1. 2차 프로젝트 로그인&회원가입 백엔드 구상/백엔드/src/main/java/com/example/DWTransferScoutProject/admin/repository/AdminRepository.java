package com.example.DWTransferScoutProject.admin.repository;

import com.example.DWTransferScoutProject.admin.entity.Admin;
import com.example.DWTransferScoutProject.common.account.repository.BaseAccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, BaseAccountRepository<Admin> {

}
