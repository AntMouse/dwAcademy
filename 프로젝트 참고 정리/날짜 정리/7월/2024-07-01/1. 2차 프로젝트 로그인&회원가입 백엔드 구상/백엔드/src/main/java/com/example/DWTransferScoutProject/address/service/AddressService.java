package com.example.DWTransferScoutProject.address.service;

import com.example.DWTransferScoutProject.address.dto.AddressDto;
import com.example.DWTransferScoutProject.address.entity.Address;
import com.example.DWTransferScoutProject.user.entity.User;
import com.example.DWTransferScoutProject.address.repository.AddressRepository;
import com.example.DWTransferScoutProject.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    public void addAddress(Long userId, AddressDto addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Address address = Address.builder()
                .recipientName(addressDto.getRecipientName())
                .contactNumber(addressDto.getContactNumber())
                .deliveryLocation(addressDto.getDeliveryLocation())
                .user(user)
                .build();

        log.info("Saving address: {}", address);
        addressRepository.save(address);
    }

    public List<AddressDto> getAddressList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        List<Address> addresses = addressRepository.findByUser(user);
        return addresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void updateAddress(Long userId, Long addressId, AddressDto addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("주소를 찾을 수 없습니다."));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        address.setRecipientName(addressDto.getRecipientName());
        address.setContactNumber(addressDto.getContactNumber());
        address.setDeliveryLocation(addressDto.getDeliveryLocation());

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

    private AddressDto convertToDto(Address address) {
        return AddressDto.builder()
                .recipientName(address.getRecipientName())
                .contactNumber(address.getContactNumber())
                .deliveryLocation(address.getDeliveryLocation())
                .build();
    }
}
