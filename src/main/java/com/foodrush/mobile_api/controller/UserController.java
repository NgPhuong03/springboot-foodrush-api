package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.response.AddressDto;
import com.foodrush.mobile_api.dto.response.ApiResponse;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.dto.response.UserCreatedResponse;
import com.foodrush.mobile_api.entity.Address;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.User;
import com.foodrush.mobile_api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ApiResponse<UserCreatedResponse> createUser(@RequestBody User user){
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(201);
        apiResponse.setResult(userService.createUser(user));

        return apiResponse;
    }

    @GetMapping("{id}")
    public ApiResponse<UserCreatedResponse> getUser(@PathVariable("id") Long id){
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(userService.getUser(id));
        return apiResponse;
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser (@RequestBody UserDto userDto,@PathVariable("id") Long id){
        UserDto udpUser = userService.updateUser(userDto,id);
        return new ResponseEntity<>(udpUser, HttpStatus.OK);
    }

    @GetMapping("{id}/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrder(@PathVariable("id") Long id){
        List<OrderResponseDto> orders = userService.getOrders(id);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("{id}/address")
    public ResponseEntity<List<AddressDto>> getAddresses(@PathVariable("id") Long id){
        List<AddressDto> address = userService.getAddress(id);
        return new ResponseEntity<>(address,HttpStatus.OK);
    }


}
