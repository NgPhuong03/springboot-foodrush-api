package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.AdminDto;
import com.foodrush.mobile_api.entity.Admin;
import com.foodrush.mobile_api.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admins")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> createAdmin (@RequestBody Admin admin){
        AdminDto adminDto = adminService.createAdmin(admin);
        return new ResponseEntity<>(adminDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminDto> getAdmin (@PathVariable("id") Long id){
        AdminDto adminDto = adminService.getAdmin(id);
        return new ResponseEntity<>(adminDto,HttpStatus.OK);
    }


}
