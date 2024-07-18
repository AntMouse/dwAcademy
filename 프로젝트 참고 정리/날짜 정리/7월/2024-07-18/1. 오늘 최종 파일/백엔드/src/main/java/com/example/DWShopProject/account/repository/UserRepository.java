package com.example.DWShopProject.account.repository;

import com.example.DWShopProject.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> ,BaseAccountRepository<User> {
    Optional<User> findByAccountId(String userId);
    List<User> findAllByEmail(String email);
    Optional<User> findByAccountIdAndEmail(String accountId, String email);
}
