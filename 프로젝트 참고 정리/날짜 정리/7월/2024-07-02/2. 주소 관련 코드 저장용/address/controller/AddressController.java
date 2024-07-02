package com.example.DWTransferScoutProject.address.controller;

import com.example.DWTransferScoutProject.address.dto.AddressDto;
import com.example.DWTransferScoutProject.auth.security.JwtUtil;
import com.example.DWTransferScoutProject.auth.security.AccountDetailsImpl;
import com.example.DWTransferScoutProject.address.service.AddressService;
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
    public ResponseEntity<?> addAddress(@AuthenticationPrincipal AccountDetailsImpl userDetails, @RequestBody AddressDto addressDto) {
        Long userId = userDetails.getAccount().getId();
        addressService.addAddress(userId, addressDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<AddressDto>> getAddressList(@AuthenticationPrincipal AccountDetailsImpl userDetails) {
        Long userId = userDetails.getAccount().getId();
        List<AddressDto> addressList = addressService.getAddressList(userId);
        return ResponseEntity.ok(addressList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAddress(@AuthenticationPrincipal AccountDetailsImpl userDetails, @PathVariable Long id, @RequestBody AddressDto addressDto) {
        Long userId = userDetails.getAccount().getId();
        addressService.updateAddress(userId, id, addressDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@AuthenticationPrincipal AccountDetailsImpl userDetails, @PathVariable Long id) {
        Long userId = userDetails.getAccount().getId();
        addressService.deleteAddress(userId, id);
        return ResponseEntity.ok().build();
    }
}
