package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {
    @Query(value = "select id from foods join orders_food_list on foods.id = orders_food_list.food_list_id group by id order by count(*) desc limit ?1", nativeQuery = true)
    List<Long> topFoodOrder(int limit);
}
