package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.response.AddressDto;
import com.foodrush.mobile_api.entity.Address;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.AddressRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressService {
    AddressRepository addressRepository;
    ModelMapper modelMapper;

    public void AddAddress(Address address){
        addressRepository.save(address);
    }

    public boolean updateAddress (Long id, AddressDto dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        address.setType(dto.getType());
        address.setTitle(dto.getTitle());
        addressRepository.save(address);
        return true;
    }

    public void deleteAddress(Long id){
        addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay address id: " + id));
        addressRepository.deleteById(id);
    }

    public AddressDto lastAddress (Long user_id) {
        Address address = addressRepository.findByLastOrder(user_id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        AddressDto dto = modelMapper.map(address ,AddressDto.class);
        return dto;
    }
}
