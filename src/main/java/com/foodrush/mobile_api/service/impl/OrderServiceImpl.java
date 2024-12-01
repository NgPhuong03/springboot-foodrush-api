package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.request.OrderCreateDto;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.entity.*;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.*;
import com.foodrush.mobile_api.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private FoodRepository foodRepository;
    private AddOnRepository addOnRepository;
    private OrderFoodRepository orderFoodRepository;
    private OrderAddonRepository orderAddonRepository;

    @Override
    @Transactional
    public void createOrder(OrderCreateDto request) {

//        Lưu dữ liệu vào trong Order
        Order order = new Order();
        order.setNote(request.getNote());
        order.setPaymethod(request.isPaymethod());
        order.setStatus("dangxuly");
        order.setCost(request.getCost());
        order.setCreated_at(new Timestamp(System.currentTimeMillis()));
        order.setAddress_id(request.getAddress_id());
        User user = new User();
        user.setId(request.getUser_id());

        order.setUser(user);
        orderRepository.save(order);

//        Lưu dữ liệu vào trong OrderFood

        request.getFoods().forEach(e -> {

            OrderFood orderFood = new OrderFood();
            Food food = foodRepository.findById(e.getFood_id()).get();
            food.setId(e.getFood_id());

            OrderFoodId id = new OrderFoodId(order.getId(),food.getId());
            orderFood.setId(id);
            orderFood.setOrder(order);
            orderFood.setFood(food);
            orderFood.setQuantity(e.getQuantity());
            orderFoodRepository.save(orderFood);
        });

//        Lưu dữ liệu vào trong OrderAddOn
        if (request.getAddons() != null) {
            request.getAddons().forEach(e -> {
                OrderAddon orderAddon = new OrderAddon();
                AddOn addon = addOnRepository.findById(e.getAddon_id()).get();
                addon.setId(e.getAddon_id());

                OrderAddonId id = new OrderAddonId(order.getId(), addon.getId());
                orderAddon.setId(id);
                orderAddon.setOrder(order);
                orderAddon.setAddOn(addon);
                orderAddon.setQuantity(e.getQuantity());
                orderAddonRepository.save(orderAddon);
            });
        }


    }

    @Override
    public OrderResponseDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Khong tim thay order id: " + id));
        OrderResponseDto responseDto = modelMapper.map(order,OrderResponseDto.class);
        responseDto.setUser_id(order.getUser().getId());
        return responseDto;
    }

    @Override
    public OrderResponseDto paidOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Khong tim thay order id: " + id));

        order.setPaid_at(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);

        OrderResponseDto responseDto = modelMapper.map(order,OrderResponseDto.class);
        responseDto.setUser_id(order.getUser().getId());
        return responseDto;
    }


}
