package com.example.DWTransferScoutProject.common.account.service;

import com.example.DWTransferScoutProject.common.account.entity.BaseAccount;
import java.util.Optional;

public interface BaseAccountService<T extends BaseAccount, D> {
    D saveAccount(D accountDto);
    T createAccount(D accountDto);
    void deleteAccount(Long id);
    D updateAccount(Long id, D dto);
    Optional<T> findById(Long id);
    Optional<T> findByAccountId(String accountId);
    D mapToDTO(T account);
}
