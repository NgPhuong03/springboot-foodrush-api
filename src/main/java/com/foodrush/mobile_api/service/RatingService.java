package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.RatingDto;
import com.foodrush.mobile_api.entity.Rating;

public interface RatingService {
    void addRating (Rating rating, Long user_id);
    RatingDto getRating (Long user_id, Long food_id);
}
