package com.example.DWShopProject.address.service;

import com.example.DWShopProject.address.dto.AddressDTO;
import com.example.DWShopProject.address.entity.Address;
import com.example.DWShopProject.account.entity.User;
import com.example.DWShopProject.address.repository.AddressRepository;
import com.example.DWShopProject.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public AddressDTO convertToDTO(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDTO.builder()
                .id(address.getId())
                .postalCode(address.getPostalCode())
                .primaryAddress(address.getPrimaryAddress())
                .detailAddress(address.getDetailAddress())
                .build();
    }

    public Address convertToEntity(AddressDTO addressDto) {
        if (addressDto == null) {
            return null;
        }
        return Address.builder()
                .id(addressDto.getId())
                .postalCode(addressDto.getPostalCode())
                .primaryAddress(addressDto.getPrimaryAddress())
                .detailAddress(addressDto.getDetailAddress())
                .build();
    }










    public void addAddress(Long userId, AddressDTO addressDto) {
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

    public List<AddressDTO> getAddressList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않음"));

        List<Address> addresses = addressRepository.findByUser(user);
        return addresses.stream()
                .map(this::convertToDto)
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

        address.setStreet(addressDto.getRecipientName());
        address.setCity(addressDto.getContactNumber());
        address.setState(addressDto.getDeliveryLocation());

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

    private AddressDTO convertToDto(Address address) {
        return AddressDTO.builder()
                .recipientName(address.getStreet())
                .contactNumber(address.getCity())
                .deliveryLocation(address.getState())
                .build();
    }
}
