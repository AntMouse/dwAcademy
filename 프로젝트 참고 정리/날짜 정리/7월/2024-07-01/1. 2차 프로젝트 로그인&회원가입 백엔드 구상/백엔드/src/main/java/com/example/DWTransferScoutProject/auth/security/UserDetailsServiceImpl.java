package com.example.DWTransferScoutProject.auth.security;

import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.admin.entity.Admin;
import com.example.DWTransferScoutProject.user.repository.UserRepository;
import com.example.DWTransferScoutProject.admin.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, AdminRepository adminRepository) {
        super();
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { // username을 userId로 변경
        User user = userRepository.findByUserId(userId).orElse(null);
        Admin admin = adminRepository.findByAdminId(userId).orElse(null);

        if (user != null) {
            return new UserDetailsImpl(user, null, user.getPassword(), user.getUserId(), user.getUserType());
        } else if (admin != null) {
            return new UserDetailsImpl(null, admin, admin.getPassword(), admin.getAdminId(), admin.getUserType());
        } else {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }

    public User findUserEntityByUserId(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public Admin findAdminEntityByAdminId(String adminId) throws UsernameNotFoundException {
        return adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("관리자를 찾을 수 없습니다."));
    }
}
