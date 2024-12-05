package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.request.FoodAddonRequest;
import com.foodrush.mobile_api.dto.response.CartResponse;

public interface CartService {
    void addToCart(FoodAddonRequest request, Long id);
    CartResponse getCart(Long id);
    void deleteCart( Long cart_id);
}
