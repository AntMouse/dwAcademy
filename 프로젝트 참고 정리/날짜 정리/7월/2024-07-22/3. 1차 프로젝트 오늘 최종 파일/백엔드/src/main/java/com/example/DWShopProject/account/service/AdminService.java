package com.example.DWShopProject.account.service;

import com.example.DWShopProject.account.common.service.AccountService;
import com.example.DWShopProject.account.dto.AdminDTO;
import com.example.DWShopProject.account.entity.Admin;
import com.example.DWShopProject.account.repository.AdminRepository;
import com.example.DWShopProject.common.security.enums.ApplicationRole;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class AdminService implements AccountService<Admin, AdminDTO> {
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin createAccount(AdminDTO adminDto) {
        Admin admin = Admin.builder()
                .accountId(adminDto.getAccountId())
                .password(passwordEncoder.encode(adminDto.getPassword()))
                .email(adminDto.getEmail())
                .accountRoles(adminDto.getAccountRoles())
                .build();

        return adminRepository.save(admin);
    }

    @Override
    public AdminDTO saveAccount(AdminDTO adminDto) {
        Admin admin = createAccount(adminDto);
        return new AdminDTO(admin);
    }

    @Override
    public AdminDTO updateAccount(Long id, AdminDTO adminDto) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        if (adminDto.getEmail() != null && !adminDto.getEmail().isEmpty()) {
            existingAdmin.updateAdminProfile(adminDto.getEmail());
        }

        if (adminDto.getAccountRoles() != null && !adminDto.getAccountRoles().isEmpty()) {
            existingAdmin.updateAccountRoles(adminDto.getAccountRoles());
        }

        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return mapToDTO(updatedAdmin);
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> findByAccountId(String accountId) {
        return adminRepository.findByAccountId(accountId);
    }

    @Override
    public AdminDTO mapToDTO(Admin admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .accountId(admin.getAccountId())
                .email(admin.getEmail())
                .registrationDate(admin.getRegistrationDate())
                .accountRoles(admin.getAccountRoles())
                .build();
    }

    @Override
    public void deleteAccount(Long id) {
        adminRepository.deleteById(id);
    }

    @Transactional
    public String login(String accountId, String password, HttpServletResponse response) {
        Optional<Admin> optionalAdmin = adminRepository.findByAccountId(accountId);

        if (optionalAdmin.isEmpty()) {
            throw new IllegalArgumentException("Admin not found");
        }

        Admin admin = optionalAdmin.get();

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        String token = jwtUtil.createToken(admin.getAccountId(), ApplicationRole.ADMIN);
        return token;
    }

    // Additional methods for admin-specific functionality can be added here
}
