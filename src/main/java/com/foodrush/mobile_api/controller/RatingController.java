package com.foodrush.mobile_api.controller;


import com.foodrush.mobile_api.dto.RatingDto;
import com.foodrush.mobile_api.dto.response.ApiResponse;
import com.foodrush.mobile_api.entity.Rating;
import com.foodrush.mobile_api.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rating")
@AllArgsConstructor
public class RatingController {
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addRating (@RequestBody Rating rating){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        ratingService.addRating(rating);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("user/{user_id}/food/{food_id}")
    public ResponseEntity<ApiResponse<RatingDto>> getRating (@PathVariable Long user_id, @PathVariable Long food_id){
        ApiResponse<RatingDto> apiResponse = new ApiResponse<>();
        RatingDto dto = ratingService.getRating(user_id,food_id);
        if (dto.getFood_id() == null){
            apiResponse.setCode(1111);// Chua tung danh gia mon an
        }
        apiResponse.setResult(dto);
        return ResponseEntity.ok(apiResponse);
    }
}
