package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.dto.response.UserCreatedResponse;
import com.foodrush.mobile_api.entity.User;

import java.util.List;

public interface UserService {
    UserCreatedResponse createUser(User user);
    UserCreatedResponse getUser(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    List<OrderResponseDto> getOrders(Long id);
}
