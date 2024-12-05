package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndAddon(Cart cart, AddOn addOn);
}
