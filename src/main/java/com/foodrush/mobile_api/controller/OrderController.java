package com.foodrush.mobile_api.controller;


import com.foodrush.mobile_api.dto.OrderDto;
import com.foodrush.mobile_api.entity.Order;
import com.foodrush.mobile_api.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto){
        Order created_order = orderService.createOrder(orderDto);
        return new ResponseEntity<>(created_order, HttpStatus.CREATED);
    }
}
