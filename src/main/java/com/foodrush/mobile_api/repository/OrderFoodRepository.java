package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.OrderFood;
import com.foodrush.mobile_api.entity.OrderFoodId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, OrderFoodId> {
}
