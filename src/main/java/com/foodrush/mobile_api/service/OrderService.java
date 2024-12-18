package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.request.OrderCreateDto;
import com.foodrush.mobile_api.dto.response.Location;
import com.foodrush.mobile_api.dto.response.OrderDetailsDto;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;

public interface OrderService {
    void createOrder(OrderCreateDto orderCreateDto);
    OrderDetailsDto getOrder(Long id);
    void chooseShipper(Long id, Long shipper);
    void changeStatus(Long id, String status);
    Location getLocation(Long id);
    Location getUserLocation(Long id);
}
