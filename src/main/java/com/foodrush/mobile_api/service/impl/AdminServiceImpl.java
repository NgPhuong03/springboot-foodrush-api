package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.AdminDto;
import com.foodrush.mobile_api.entity.Admin;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.AdminRepository;
import com.foodrush.mobile_api.service.AdminService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private ModelMapper modelMapper;

    @Override
    public AdminDto createAdmin(Admin admin) {
        adminRepository.save(admin);
        AdminDto adminDto = modelMapper.map(admin, AdminDto.class);
        return adminDto;
    }

    @Override
    public AdminDto getAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Khong tim thay admin id: " + id));
        return modelMapper.map(admin,AdminDto.class);
    }


}
