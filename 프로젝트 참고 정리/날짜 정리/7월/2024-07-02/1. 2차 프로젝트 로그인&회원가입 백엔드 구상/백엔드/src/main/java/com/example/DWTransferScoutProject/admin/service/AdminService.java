package com.example.DWTransferScoutProject.admin.service;

import com.example.DWTransferScoutProject.admin.dto.AdminDto;
import com.example.DWTransferScoutProject.admin.entity.Admin;
import com.example.DWTransferScoutProject.admin.repository.AdminRepository;
import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.common.account.service.BaseAccountService;
import com.example.DWTransferScoutProject.user.dto.UserDto;
import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService implements BaseAccountService<Admin, AdminDto> {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin signUp(AdminDto adminDto) {
        Admin admin = Admin.builder()
                .accountId(adminDto.getAdminId())
                .password(passwordEncoder.encode(adminDto.getPassword()))
                .adminEmail(adminDto.getAdminEmail()) // 수정된 부분
                .accountType(ApplicationRoleEnum.ADMIN)
                .build();

        if (adminRepository.findByAccountId(admin.getAccountId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + admin.getAccountId());
        }

        Admin savedAdmin = adminRepository.save(admin);
        return savedAdmin;
    }

    @Override
    public void deleteAccount(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public AdminDto updateAccount(Long id, AdminDto adminDto) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        existingAdmin.updateAdminInfo(
                adminDto.getAdminEmail(),
                adminDto.getAccountType()
        );

        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return mapToDTO(updatedAdmin);
    }

    // AdminService에 추가할 비밀번호 변경 메서드
    public void updateAdminPassword(Long id, String newPassword) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        existingAdmin.updateAdminPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(existingAdmin);
    }

    // edit 메서드 추가
    public AdminDto edit(AdminDto adminDto) {
        return updateAccount(adminDto.getId(), adminDto);
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
    public AdminDto mapToDTO(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .adminId(admin.getAccountId())
                .password(null) // 비밀번호는 반환하지 않음
                .adminEmail(admin.getAdminEmail())
                .accountType(admin.getAccountType())
                .build();
    }

    // 관리자가 사용자 정보를 업데이트하는 메서드
    public UserDto AdminupdateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userDto.getAccountType() != null) {
            existingUser.updateAccountType(userDto.getAccountType());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToUserDTO(updatedUser);
    }


    public UserDto mapToUserDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userId(user.getAccountId())
                .accountType(user.getAccountType())
                .username(user.getUsername())
                .password(null)
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .userEmail(user.getUserEmail())
                .contact(user.getContact())
                .build();
    }
}
