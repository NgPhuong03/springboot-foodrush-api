package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.OrderDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.entity.Order;
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
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        UserDto savedUser= userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto userDto = userService.getUser(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser (@RequestBody UserDto userDto,@PathVariable("id") Long id){
        UserDto udpUser = userService.updateUser(userDto,id);
        return new ResponseEntity<>(udpUser, HttpStatus.OK);
    }

    @GetMapping("{id}/orders")
    public ResponseEntity<List<OrderDto>> getOrder(@PathVariable("id") Long id){
        List<OrderDto> orders = userService.getOrders(id);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
