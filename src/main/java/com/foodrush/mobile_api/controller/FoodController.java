package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.dto.response.HomeScreenFood;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/foods")
@AllArgsConstructor
public class FoodController {
    private FoodService foodService;

    @PostMapping
    public ResponseEntity<String> createFood(@RequestBody Food food){
        foodService.createFood(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<FoodDto> getFood(@PathVariable Long id) {
        FoodDto food = foodService.getFood(id);
        return ResponseEntity.ok(food);
    }

    @GetMapping
    public ResponseEntity<List<FoodDto>> getListFood(@RequestParam(name = "type") String type, @RequestParam(name = "limit") int limit){
        List<FoodDto> foodList = new ArrayList<>();

        switch (type){
            case "topseller":
                foodList = foodService.getTopOrder(limit);
                break;
            case "rating":
                foodList = foodService.getTopRating(limit);
                break;
            default:
                foodList = foodService.getTopSale(limit);
                break;
        }
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("/alltop")
    public ResponseEntity<HomeScreenFood> getAllTop(){
        return ResponseEntity.ok(foodService.getAllTop());
    }

    @GetMapping("/category")
    public ResponseEntity<List<FoodDto>> getByCategory(@RequestParam(name = "category") String category){
        List<FoodDto> foodDto = switch (category) {
            case "rice" -> foodService.getCategoryRice();
            case "noodle" -> foodService.getCategoryNoodle();
            case "mon40k" -> foodService.getCategoryMon40k();
            case "vegan" -> foodService.getCategoryVegan();
            default -> foodService.getAll();
        };
        return ResponseEntity.ok(foodDto);
    }



}
