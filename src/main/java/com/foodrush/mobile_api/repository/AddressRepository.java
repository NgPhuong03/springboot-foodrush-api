package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
