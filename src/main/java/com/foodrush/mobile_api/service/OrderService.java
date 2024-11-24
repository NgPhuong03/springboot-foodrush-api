package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.OrderDto;
import com.foodrush.mobile_api.entity.Order;

public interface OrderService {
    Order createOrder(OrderDto orderDto);
}
