package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query(value = "select *\n" +
            "from rating\n" +
            "where user_Id = :user_id and food_id = :food_id\n",nativeQuery = true)
    Optional<Rating> getByUserAndFood(Long user_id, Long food_id);
}
