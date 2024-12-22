package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.response.ApiResponse;
import com.foodrush.mobile_api.dto.response.Location;
import com.foodrush.mobile_api.service.ShipperService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shipper")
@AllArgsConstructor
public class ShipperController {
    ShipperService shipperService;

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<String>> updatePosition (@PathVariable Long id, @RequestBody Location location) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if (shipperService.updateLocation(location, id)){
            apiResponse.setMessage("Cap nhat thanh cong");
        } else {
            apiResponse.setMessage("Cap nhat that bai");
        }
        return ResponseEntity.ok(apiResponse);
    }
}
