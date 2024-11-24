package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
