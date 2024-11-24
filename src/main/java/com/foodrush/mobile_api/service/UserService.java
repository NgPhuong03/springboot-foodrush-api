package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.OrderDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.entity.Order;
import com.foodrush.mobile_api.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(User user);
    UserDto getUser(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    List<OrderDto> getOrders(Long id);
}
