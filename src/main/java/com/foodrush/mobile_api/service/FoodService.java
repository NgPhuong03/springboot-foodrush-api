package com.foodrush.mobile_api.service;


import com.foodrush.mobile_api.entity.Food;

import java.util.List;

public interface FoodService {
    void createFood (Food food);
    Food getFood (Long id);
    List<Food> getAll();
    List<Food> getTopOrder(int limit);
}
