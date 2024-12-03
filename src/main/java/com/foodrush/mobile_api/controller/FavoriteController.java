package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.dto.response.ApiResponse;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/{id}/favorites")
@AllArgsConstructor
public class FavoriteController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<FoodDto>> getFavorites (@PathVariable("id") Long user_id){
        ApiResponse<FoodDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getFavorites(user_id));
        apiResponse.setMessage("OK");
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<ApiResponse> addFavoriteFood (@PathVariable("id") Long user_id, @RequestBody Food food){
        ApiResponse apiResponse = new ApiResponse<>();
        userService.addFavoriteFood(user_id,food);
        apiResponse.setMessage("OK");
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("{food_id}")
    public ResponseEntity<ApiResponse> deleteFavoriteFood (@PathVariable("id") Long user_id, @PathVariable Long food_id){
        ApiResponse apiResponse = new ApiResponse<>();
        userService.deleteFavoriteFood(user_id,food_id);
        apiResponse.setMessage("OK");
        return ResponseEntity.ok(apiResponse);
    }
}