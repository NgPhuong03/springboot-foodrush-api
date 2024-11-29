package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipperRepository extends JpaRepository<Shipper,Long> {
    Optional<Shipper> findByEmail(String email);
}
