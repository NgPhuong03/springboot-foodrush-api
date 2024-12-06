package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Order;
import com.foodrush.mobile_api.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
    Optional<List<OrderFood>> findByOrder(Order order);
}
