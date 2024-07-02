package com.example.DWTransferScoutProject.admin.controller;

import com.example.DWTransferScoutProject.admin.dto.AdminDto;
import com.example.DWTransferScoutProject.admin.service.AdminService;
import com.example.DWTransferScoutProject.common.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    // Admin 생성 API
    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(adminService.mapToDTO(adminService.signUp(adminDto)));
    }

    // Admin 업데이트 API
    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(adminService.updateAccount(id, adminDto));
    }

    // Admin 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    // Admin 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.mapToDTO(
                adminService.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id))
        ));
    }
}
