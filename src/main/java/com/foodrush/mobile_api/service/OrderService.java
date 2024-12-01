package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.request.OrderCreateDto;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;

public interface OrderService {
    void createOrder(OrderCreateDto orderCreateDto);
    OrderResponseDto getOrder(Long id);
    OrderResponseDto paidOrder(Long id);
}
