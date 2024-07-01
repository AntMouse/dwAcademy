package com.example.DWTransferScoutProject.admin.service;

import com.example.DWTransferScoutProject.admin.dto.AdminDto;
import com.example.DWTransferScoutProject.admin.entity.Admin;
import com.example.DWTransferScoutProject.admin.repository.AdminRepository;
import com.example.DWTransferScoutProject.auth.security.ApplicationRoleEnum;
import com.example.DWTransferScoutProject.user.dto.UserDto;
import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setAdminId(adminDto.getAdminId()); // 기존 userId에서 변경
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setEmail(adminDto.getEmail());
        admin.setUserType(ApplicationRoleEnum.ADMIN);

        if (adminRepository.findByAdminId(admin.getAdminId()).isPresent()) { // 기존 findByUserId에서 변경
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다: " + admin.getAdminId()); // 기존 userId에서 변경
        }

        Admin savedAdmin = adminRepository.save(admin);
        return mapToDTO(savedAdmin);
    }

    public AdminDto updateAdmin(Long id, AdminDto adminDto) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        if (adminDto.getAdminId() != null) { // 기존 userId에서 변경
            existingAdmin.setAdminId(adminDto.getAdminId()); // 기존 userId에서 변경
        }
        if (adminDto.getPassword() != null) {
            existingAdmin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        }
        if (adminDto.getEmail() != null) {
            existingAdmin.setEmail(adminDto.getEmail());
        }
        if (adminDto.getUserType() != null) {
            existingAdmin.setUserType(adminDto.getUserType());
        }

        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return mapToDTO(updatedAdmin);
    }

    public AdminDto mapToDTO(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .adminId(admin.getAdminId()) // 기존 userId에서 변경
                .userType(admin.getUserType())
                .email(admin.getEmail())
                .password(null) // 비밀번호 필드를 null로 설정
                .build();
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Optional<Admin> findAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // 관리자가 사용자 정보를 업데이트하는 메서드
    public UserDto AdminupdateMember(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 사용자 정보 업데이트
        if (userDto.getUserId() != null) {
            existingUser.setUserId(userDto.getUserId());
        }
        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getBirthdate() != null) {
            existingUser.setBirthdate(userDto.getBirthdate());
        }
        if (userDto.getGender() != null) {
            existingUser.setGender(userDto.getGender());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getContact() != null) {
            existingUser.setContact(userDto.getContact());
        }
        if (userDto.getUserType() != null) {
            existingUser.setUserType(userDto.getUserType());
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToUserDTO(updatedUser);
    }

    public UserDto mapToUserDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userType(user.getUserType())
                .username(user.getUsername())
                .password(null) // 비밀번호 필드를 null로 설정
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
    }
}
