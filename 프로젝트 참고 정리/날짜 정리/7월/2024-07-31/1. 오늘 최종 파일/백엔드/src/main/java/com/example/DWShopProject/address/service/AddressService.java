package com.example.DWShopProject.address.service;

import com.example.DWShopProject.address.converter.AddressDTOConverter;
import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.common.exception.UnauthorizedAccessException;
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
                .orElseThrow(() -> new ResourceNotFoundException("회원이 존재하지 않음"));

        Address address = addressDtoConverter.convertToAddressEntityWithUser(addressDto, user);

        log.info("Saving address: {}", address);
        addressRepository.save(address);
    }

    public void deleteAddress(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("회원이 존재하지 않음"));

        Address address = user.getAddress();

        if (!address.getUser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }

        addressRepository.delete(address);
    }

    public AddressDTO getAddressByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("회원이 존재하지 않음"));

        Address address = addressRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("주소가 존재하지 않음"));

        return addressDtoConverter.convertToAddressDto(address);
    }

    public void updateAddress(Long userId, Long addressId, AddressDTO addressDto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("주소를 찾을 수 없습니다."));

        if (!address.getUser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }

        address.updateAddressDetails(addressDto.getPostalCode(), addressDto.getPrimaryAddress(), addressDto.getDetailAddress());

        addressRepository.save(address);
    }
}
