package com.foodrush.mobile_api.controller;


import com.foodrush.mobile_api.dto.request.OrderCreateDto;
import com.foodrush.mobile_api.dto.response.ApiResponse;
import com.foodrush.mobile_api.dto.response.Location;
import com.foodrush.mobile_api.dto.response.OrderDetailsDto;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderCreateDto>> createOrder(@RequestBody OrderCreateDto orderCreateDto){
        orderService.createOrder(orderCreateDto);
        ApiResponse<OrderCreateDto> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("OK");
        apiResponse.setResult(orderCreateDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<OrderDetailsDto>> getOrder(@PathVariable Long id){
        ApiResponse<OrderDetailsDto> responseDto = new ApiResponse<>();
        responseDto.setResult(orderService.getOrder(id));
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("{id}/shipper/{shipper_id}")
    public ResponseEntity<ApiResponse> chooseShipper(@PathVariable Long id, @PathVariable  Long shipper_id){
        ApiResponse responseDto = new ApiResponse<>();
        orderService.chooseShipper(id,shipper_id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("{id}/location")
    public ResponseEntity<ApiResponse<Location>> getLocation(@PathVariable Long id){
        ApiResponse<Location> responseDto = new ApiResponse<>();
        responseDto.setResult(orderService.getLocation(id));
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse> changeStatus (@PathVariable Long id, @RequestParam(name = "status",defaultValue = "choxacnhan")String status ){
        ApiResponse response = new ApiResponse<>();
        orderService.changeStatus(id,status);
        response.setMessage("Ok");
        return ResponseEntity.ok(response);
    }



}
