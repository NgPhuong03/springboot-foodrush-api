package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.entity.Address;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.AddressRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressService {
    AddressRepository addressRepository;

    public void AddAddress(Address address){
        addressRepository.save(address);
    }

    public void deleteAddress(Long id){
        addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay address id: " + id));
        addressRepository.deleteById(id);
    }
}
