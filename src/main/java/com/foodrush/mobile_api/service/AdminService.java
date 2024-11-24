package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.AdminDto;
import com.foodrush.mobile_api.entity.Admin;

public interface AdminService {
    AdminDto createAdmin (Admin admin);
    AdminDto getAdmin (Long id);
}
