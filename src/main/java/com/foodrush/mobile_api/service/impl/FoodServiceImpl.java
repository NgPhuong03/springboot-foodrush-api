package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.dto.response.HomeScreenFood;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.FoodRepository;
import com.foodrush.mobile_api.service.FoodService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public FoodDto getFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay food id: " + id));
        FoodDto dto = modelMapper.map(food,FoodDto.class);
        dto.setStar(foodRepository.getRating(id));
        dto.setCount_rv(foodRepository.getCount_rv(id));
        return dto;
    }

    @Override
    public List<FoodDto> getAll() {
        List<FoodDto> foodList = foodRepository.findAll().stream()
                .map( e -> modelMapper.map(e,FoodDto.class))
                .toList();
        return foodList;
    }

    @Override
    public List<FoodDto> getTopOrder(int limit) {
        List<Long> idList = foodRepository.topFoodOrder(limit).stream().toList();
        List<FoodDto> foodList = new ArrayList<>();
        foodList = idList.stream()
                .map(id -> {
                    Food food = foodRepository.findById(id).get();
                    FoodDto dto = modelMapper.map(food, FoodDto.class);
                    dto.setCount_rv(foodRepository.getCount_rv(id));
                    dto.setStar(foodRepository.getRating(id));
                    return dto;
                }).toList();
        return foodList;
    }

    @Override
    public List<FoodDto> getTopRating(int limit) {
        List<Long> idList = foodRepository.topFoodRating(limit).stream().toList();
        List<FoodDto> foodList = new ArrayList<>();
        foodList = idList.stream()
                .map(id -> {
                    Food food = foodRepository.findById(id).get();
                    FoodDto dto = modelMapper.map(food, FoodDto.class);
                    dto.setCount_rv(foodRepository.getCount_rv(id));
                    dto.setStar(foodRepository.getRating(id));
                    return dto;
                }).toList();
        return foodList;
    }

    @Override
    public HomeScreenFood getAllTop() {
        HomeScreenFood item = HomeScreenFood.builder()
                .topSale(getTopSale(10))
                .topOrder(getTopOrder(10))
                .topRating(getTopRating(10))
                .build();
        return item;
    }

    @Override
    public List<FoodDto> getCategoryRice() {
        List<Long> idList = foodRepository.getCategoryRice().stream().toList();
        List<FoodDto> foodList = new ArrayList<>();
        foodList = idList.stream()
                .map(id -> {
                    Food food = foodRepository.findById(id).get();
                    FoodDto dto = modelMapper.map(food, FoodDto.class);
                    dto.setCount_rv(foodRepository.getCount_rv(id));
                    dto.setStar(foodRepository.getRating(id));
                    return dto;
                }).toList();
        return foodList;
    }

    @Override
    public List<FoodDto> getCategoryNoodle() {
        List<Long> idList = foodRepository.getCategoryNoodle().stream().toList();
        List<FoodDto> foodList = new ArrayList<>();
        foodList = idList.stream()
                .map(id -> {
                    Food food = foodRepository.findById(id).get();
                    FoodDto dto = modelMapper.map(food, FoodDto.class);
                    dto.setCount_rv(foodRepository.getCount_rv(id));
                    dto.setStar(foodRepository.getRating(id));
                    return dto;
                }).toList();
        return foodList;
    }

    @Override
    public List<FoodDto> getCategoryMon40k() {
        List<Long> idList = foodRepository.getCategoryMon40k().stream().toList();
        List<FoodDto> foodList = new ArrayList<>();
        foodList = idList.stream()
                .map(id -> {
                    Food food = foodRepository.findById(id).get();
                    FoodDto dto = modelMapper.map(food, FoodDto.class);
                    dto.setCount_rv(foodRepository.getCount_rv(id));
                    dto.setStar(foodRepository.getRating(id));
                    return dto;
                }).toList();
        return foodList;
    }

    @Override
    public List<FoodDto> getCategoryVegan() {
        List<Long> idList = foodRepository.getCategoryVegan().stream().toList();
        List<FoodDto> foodList = new ArrayList<>();
        foodList = idList.stream()
                .map(id -> {
                    Food food = foodRepository.findById(id).get();
                    FoodDto dto = modelMapper.map(food, FoodDto.class);
                    dto.setCount_rv(foodRepository.getCount_rv(id));
                    dto.setStar(foodRepository.getRating(id));
                    return dto;
                }).toList();
        return foodList;
    }


    @Override
    public List<FoodDto> getTopSale(int limit) {
        List<Long> idList = foodRepository.topFoodSale(limit).stream().toList();
        List<FoodDto> foodList = new ArrayList<>();
        foodList = idList.stream()
                .map(id -> {
                    Food food = foodRepository.findById(id).get();
                    FoodDto dto = modelMapper.map(food, FoodDto.class);
                    dto.setCount_rv(foodRepository.getCount_rv(id));
                    dto.setStar(foodRepository.getRating(id));
                    return dto;
                }).toList();
        return foodList;
    }
}
