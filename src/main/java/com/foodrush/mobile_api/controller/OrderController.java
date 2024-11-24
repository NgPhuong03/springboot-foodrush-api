package com.foodrush.mobile_api.controller;


import com.foodrush.mobile_api.dto.OrderCreateDto;
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
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto){
        OrderResponseDto created_order = orderService.createOrder(orderCreateDto);
        return new ResponseEntity<>(created_order, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long id){
        OrderResponseDto responseDto = orderService.getOrder(id);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("{id}/paid")
    public ResponseEntity<OrderResponseDto> paidOrder(@PathVariable Long id){
        OrderResponseDto responseDto = orderService.paidOrder(id);
        return ResponseEntity.ok(responseDto);
    }



}
