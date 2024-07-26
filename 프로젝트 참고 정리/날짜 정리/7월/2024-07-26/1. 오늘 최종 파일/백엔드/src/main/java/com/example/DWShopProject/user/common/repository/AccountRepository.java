package com.example.DWShopProject.user.common.repository;

import com.example.DWShopProject.user.common.entity.Account;

import java.util.Optional;

public interface AccountRepository<T extends Account> {
    Optional<T> findByAccountId(String accountId);
}
