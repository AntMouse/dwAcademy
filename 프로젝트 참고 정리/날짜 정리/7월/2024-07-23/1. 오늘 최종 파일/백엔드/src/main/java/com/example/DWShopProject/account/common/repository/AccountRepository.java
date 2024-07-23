package com.example.DWShopProject.account.common.repository;

import com.example.DWShopProject.account.common.entity.Account;

import java.util.Optional;

public interface AccountRepository<T extends Account> {
    Optional<T> findByAccountId(String accountId);
}
