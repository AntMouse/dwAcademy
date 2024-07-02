package com.example.DWTransferScoutProject.admin.repository;

import com.example.DWTransferScoutProject.admin.entity.Admin;
import com.example.DWTransferScoutProject.common.account.repository.BaseAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends BaseAccountRepository<Admin> {

}
