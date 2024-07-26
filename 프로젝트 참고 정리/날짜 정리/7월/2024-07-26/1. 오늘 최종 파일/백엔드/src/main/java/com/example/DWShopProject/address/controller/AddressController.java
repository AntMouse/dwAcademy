package com.example.DWShopProject.address.controller;

import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.common.security.userdetails.UserDetailsImpl;
import com.example.DWShopProject.address.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody AddressDTO addressDto) {
        Long userId = userDetails.getUser().getId();
        addressService.addAddress(userId, addressDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<AddressDTO>> getAddressList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        List<AddressDTO> addressList = addressService.getAddressList(userId);
        return ResponseEntity.ok(addressList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody AddressDTO addressDto) {
        Long userId = userDetails.getUser().getId();
        addressService.updateAddress(userId, id, addressDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        Long userId = userDetails.getUser().getId();
        addressService.deleteAddress(userId, id);
        return ResponseEntity.ok().build();
    }
}




