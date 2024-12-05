package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.OrderFood;
import com.foodrush.mobile_api.entity.OrderFoodAddon;
import com.foodrush.mobile_api.entity.custom.OrderAddonId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFoodAddonRepository extends JpaRepository<OrderFoodAddon, OrderAddonId> {
    List<OrderFoodAddon> findByOrderFood(OrderFood orderFood);
}
