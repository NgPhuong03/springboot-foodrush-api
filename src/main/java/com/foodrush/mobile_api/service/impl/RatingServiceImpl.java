package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.RatingDto;
import com.foodrush.mobile_api.entity.Rating;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.repository.RatingRepository;
import com.foodrush.mobile_api.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    RatingRepository ratingRepository;

    @Override
    public void addRating(Rating rating) {
        rating.setCreated_at(new Timestamp(System.currentTimeMillis()));
        ratingRepository.save(rating);
    }

    @Override
    public RatingDto getRating(Long user_id, Long food_id) {
        if (ratingRepository.getByUserAndFood(user_id,food_id).isPresent()){
            Rating rating = ratingRepository.getByUserAndFood(user_id,food_id).get();
            return new RatingDto(rating.getFood_id(), rating.getStar());
        } else {
            return new RatingDto();
        }
    }
}
