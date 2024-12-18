package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {
    @Query(value = "select foods.id from foods join order_food on foods.id = order_food.food_id group by foods.id order by count(*) desc limit ?1", nativeQuery = true)
    List<Long> topFoodOrder(int limit);

    @Query(value = "select foods.id\n" +
            "from foods join rating on foods.id = rating.food_id\n" +
            "group by foods.id \n" +
            "order by avg(star) desc\n" +
            "limit ?\n", nativeQuery = true)
    List<Long> topFoodRating(int limit);

    @Query(value = "select id\n" +
            "from foods \n" +
            "where sale > 0\n" +
            "order by sale desc\n" +
            "limit ?", nativeQuery = true)
    List<Long> topFoodSale(int limit);

    @Query(value = "select AVG(star) from rating where food_id = ?1", nativeQuery = true)
    float getRating(Long id);

    @Query(value = "select count(*) from rating where food_id = ?",nativeQuery = true)
    int getCount_rv(Long id);

    @Query(value = "select food_id from category where rice = true", nativeQuery = true)
    List<Long> getCategoryRice();
    @Query(value = "select food_id from category where noodle = true", nativeQuery = true)
    List<Long> getCategoryNoodle();
    @Query(value = "select food_id from category where mon40k = true", nativeQuery = true)
    List<Long> getCategoryMon40k();
    @Query(value = "select food_id from category where vegan = true", nativeQuery = true)
    List<Long> getCategoryVegan();



}
