package com.example.DWShopProject.address.controller;

import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.common.security.jwt.JwtUtil;
import com.example.DWShopProject.common.security.userdetails.UserDetailsImpl;
import com.example.DWShopProject.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final JwtUtil jwtUtil;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody AddressDTO addressDto) {
        Long userId = userDetails.getUser().getId();
        addressService.addAddress(userId, addressDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        addressService.deleteAddress(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/address")
    public ResponseEntity<AddressDTO> getAddress(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        AddressDTO addressDto = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(addressDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody AddressDTO addressDto) {
        Long userId = userDetails.getUser().getId();
        addressService.updateAddress(userId, id, addressDto);
        return ResponseEntity.ok().build();
    }
}




