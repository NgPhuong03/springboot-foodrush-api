package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.request.FoodQuantity;
import com.foodrush.mobile_api.dto.request.OrderCreateDto;
import com.foodrush.mobile_api.dto.response.AddressDto;
import com.foodrush.mobile_api.dto.response.ApiResponse;
import com.foodrush.mobile_api.entity.*;
import com.foodrush.mobile_api.service.AddressService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<String>> updateAddress (@PathVariable("id") Long id, @RequestBody AddressDto item){
        ApiResponse<String> apiResponse = new ApiResponse<>();
         if (addressService.updateAddress(id, item)){
             apiResponse.setResult("Cap nhat thanh cong");
         } else {
             apiResponse.setResult("Cap nhat that bai");
         }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{id}/last_order")
    public ResponseEntity<ApiResponse<AddressDto>> lastAddressOrder (@PathVariable("id") Long id){
        ApiResponse<AddressDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(addressService.lastAddress(id));
        return ResponseEntity.ok(apiResponse);
    }
}
