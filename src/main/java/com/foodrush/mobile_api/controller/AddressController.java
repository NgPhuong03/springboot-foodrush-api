package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.entity.Address;
import com.foodrush.mobile_api.entity.User;
import com.foodrush.mobile_api.repository.AddressRepository;
import com.foodrush.mobile_api.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @PostMapping("{id}/add")
    public ResponseEntity<String> AddAddress (@RequestBody Address address, @PathVariable Long id){
        User user = new User();
        user.setId(id);
        address.setUser(user);

        addressService.AddAddress(address);

        return ResponseEntity.ok("Cap nhat thanh cong");
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteAddress ( @PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Cap nhat thanh cong");
    }
}
