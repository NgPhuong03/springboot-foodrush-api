package com.foodrush.mobile_api.controller;

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
    public ResponseEntity<Food> getFood(@PathVariable Long id) {
        Food food = foodService.getFood(id);
        return ResponseEntity.ok(food);
    }

    @GetMapping
    public ResponseEntity<List<Food>> getTypeFood(@RequestParam(name = "type") String type, @RequestParam(name = "limit") int limit){
        List<Food> foodList = new ArrayList<>();

        switch (type){
            case "topseller":
                foodList = foodService.getTopOrder(limit);
                break;
            default:
                foodList = foodService.getAll();
                break;
        }

        return ResponseEntity.ok(foodList);
    }




}
