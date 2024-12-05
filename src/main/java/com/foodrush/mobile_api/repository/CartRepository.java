package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Cart;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserAndFood(User user, Food food);
    Optional<Cart> findByUser(User user);
}
