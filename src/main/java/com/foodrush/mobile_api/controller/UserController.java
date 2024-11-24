package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.dto.response.UserCreatedResponse;
import com.foodrush.mobile_api.entity.User;
import com.foodrush.mobile_api.service.UserService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<UserCreatedResponse> createUser(@RequestBody User user){
        UserCreatedResponse savedUser= userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserCreatedResponse> getUser(@PathVariable("id") Long id){
        UserCreatedResponse user = userService.getUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
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


}
