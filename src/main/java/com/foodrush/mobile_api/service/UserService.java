package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.dto.response.AddressDto;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.dto.response.UserCreatedResponse;
import com.foodrush.mobile_api.entity.Address;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.User;

import java.util.List;

public interface UserService {
    UserCreatedResponse createUser(User user);
    UserCreatedResponse getUser(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    List<OrderResponseDto> getOrders(Long id);
    List<AddressDto> getAddress(Long id);
    void addFavoriteFood(Long id,Long food_id);
    void deleteFavoriteFood(Long user_id, Long food_id);
    List<FoodDto> getFavorites(Long id);
}
