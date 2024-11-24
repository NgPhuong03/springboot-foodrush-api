package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
