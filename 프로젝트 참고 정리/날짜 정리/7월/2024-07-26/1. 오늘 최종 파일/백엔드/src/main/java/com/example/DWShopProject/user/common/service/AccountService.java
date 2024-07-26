package com.example.DWShopProject.user.common.service;

import com.example.DWShopProject.user.common.entity.Account;

import java.util.Optional;

public interface AccountService<T extends Account, D> {
    T createAccount(D accountDto);
    D saveAccount(D accountDto);
    void deleteAccount(Long id);
    D updateAccount(Long id, D dto);
    Optional<T> findById(Long id);
    Optional<T> findByAccountId(String accountId);
    D mapToDTO(T account);
}
