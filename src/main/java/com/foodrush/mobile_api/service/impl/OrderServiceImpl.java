package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.OrderCreateDto;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.Order;
import com.foodrush.mobile_api.entity.User;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.FoodRepository;
import com.foodrush.mobile_api.repository.OrderRepository;
import com.foodrush.mobile_api.repository.UserRepository;
import com.foodrush.mobile_api.service.OrderService;
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
    private UserRepository userRepository;
    private FoodRepository foodRepository;

    @Override
    public OrderResponseDto createOrder(OrderCreateDto orderCreateDto) {
        Order order = modelMapper.map(orderCreateDto,Order.class);

        User user = userRepository.findById(orderCreateDto.getUser_id()).get();
        List<Food> foodList = orderCreateDto.getFoodList().stream()
                .map(e -> foodRepository.findById(e).get()).toList();

        order.setCreated_at(new Timestamp(System.currentTimeMillis()));
        order.setUser(user);
        order.setFoodList(foodList);
        orderRepository.save(order);
        OrderResponseDto responseDto = modelMapper.map(orderCreateDto, OrderResponseDto.class);

        responseDto.setUser_id(user.getId());
        responseDto.setFoodList(foodList);
        return responseDto;
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
