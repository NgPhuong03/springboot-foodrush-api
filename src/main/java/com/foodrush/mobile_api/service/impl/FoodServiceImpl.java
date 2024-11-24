package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.FoodRepository;
import com.foodrush.mobile_api.service.FoodService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {
    private FoodRepository foodRepository;
    private ModelMapper modelMapper;

    @Override
    public void createFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public Food getFood(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay food id: " + id));
    }

    @Override
    public List<Food> getAll() {
        List<Food> foodList = foodRepository.findAll();
        return foodList;
    }
}
