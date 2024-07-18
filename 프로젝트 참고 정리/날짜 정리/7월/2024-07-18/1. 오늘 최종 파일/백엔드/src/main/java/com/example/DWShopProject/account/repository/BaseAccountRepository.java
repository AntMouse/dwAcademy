package com.example.DWShopProject.account.repository;

import com.example.DWShopProject.account.entity.BaseAccount;

import java.util.Optional;

public interface BaseAccountRepository<T extends BaseAccount> {
    Optional<T> findByAccountId(String accountId);
}
