package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
