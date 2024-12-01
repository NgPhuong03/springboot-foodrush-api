package com.foodrush.mobile_api.service;


import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.dto.response.HomeScreenFood;
import com.foodrush.mobile_api.entity.Food;

import java.util.List;

public interface FoodService {
    void createFood (Food food);
    FoodDto getFood (Long id);
    List<FoodDto> getAll();
    List<FoodDto> getTopOrder(int limit);
    List<FoodDto> getTopSale(int limit);
    List<FoodDto> getTopRating(int limit);
    HomeScreenFood getAllTop();
    List<FoodDto> getCategoryRice();
    List<FoodDto> getCategoryNoodle();
    List<FoodDto> getCategoryMon40k();
    List<FoodDto> getCategoryVegan();
}
