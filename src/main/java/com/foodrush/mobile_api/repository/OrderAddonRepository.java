package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.OrderFoodAddon;
import com.foodrush.mobile_api.entity.custom.OrderAddonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddonRepository extends JpaRepository<OrderFoodAddon, OrderAddonId> {
}
