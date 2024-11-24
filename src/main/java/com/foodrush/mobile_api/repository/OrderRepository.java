package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
