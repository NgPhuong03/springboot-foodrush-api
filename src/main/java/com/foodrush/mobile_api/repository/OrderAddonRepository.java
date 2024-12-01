package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.OrderAddon;
import com.foodrush.mobile_api.entity.OrderAddonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddonRepository extends JpaRepository<OrderAddon, OrderAddonId> {
}
