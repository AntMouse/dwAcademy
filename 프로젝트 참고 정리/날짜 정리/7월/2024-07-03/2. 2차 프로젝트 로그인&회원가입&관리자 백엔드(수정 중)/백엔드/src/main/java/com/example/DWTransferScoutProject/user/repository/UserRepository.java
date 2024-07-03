package com.example.DWTransferScoutProject.user.repository;

import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.common.account.repository.BaseAccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, BaseAccountRepository<User> {
    Optional<User> findByUsername(String username);
    List<User> findAllByUserEmail(String userEmail);
    Optional<User> findByUsernameAndUserEmail(String username, String userEmail);
}
