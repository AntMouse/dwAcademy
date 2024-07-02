package com.example.DWTransferScoutProject.common.account.repository;

import com.example.DWTransferScoutProject.common.account.entity.BaseAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BaseAccountRepository<T extends BaseAccount> extends JpaRepository<T, Long> {
    Optional<T> findByAccountId(String accountId);
}
