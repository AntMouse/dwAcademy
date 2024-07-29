package com.example.DWShopProject.address.service;

import com.example.DWShopProject.address.converter.AddressDTOConverter;
import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.user.entity.User;
import com.example.DWShopProject.address.repository.AddressRepository;
import com.example.DWShopProject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressDTOConverter addressDtoConverter;

    public void addAddress(Long userId, AddressDTO addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Address address = Address.builder()
                .postalCode(addressDto.getPostalCode())
                .primaryAddress(addressDto.getPrimaryAddress())
                .detailAddress(addressDto.getDetailAddress())
                .user(user)
                .build();

        log.info("Saving address: {}", address);

        addressRepository.save(address);
    }

    public List<AddressDTO> getAddressList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        List<Address> addresses = addressRepository.findByUser(user);
        return addresses.stream()
                .map(addressDtoConverter::convertToAddressDto)
                .collect(Collectors.toList());
    }

    public void updateAddress(Long userId, Long addressId, AddressDTO addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("주소를 찾을 수 없습니다."));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        address.updateAddressDetails(addressDto.getPostalCode(), addressDto.getPrimaryAddress(), addressDto.getDetailAddress());

        addressRepository.save(address);
    }

    public void deleteAddress(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("주소를 찾을 수 없습니다."));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        addressRepository.delete(address);
    }
}
