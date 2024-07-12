package com.example.DWShopProject.account.service;

import com.example.DWShopProject.account.entity.BaseAccount;

import java.util.Optional;

public interface BaseAccountService<T extends BaseAccount, D> {
    T createAccount(D accountDto);
    D saveAccount(D accountDto);
    void deleteAccount(Long id);
    D updateAccount(Long id, D dto);
    Optional<T> findById(Long id);
    Optional<T> findByAccountId(String accountId);
    D mapToDTO(T account);
}
