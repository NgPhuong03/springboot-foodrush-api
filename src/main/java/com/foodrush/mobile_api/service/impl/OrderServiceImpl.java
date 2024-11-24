package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.OrderDto;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.Order;
import com.foodrush.mobile_api.entity.User;
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
    public Order createOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto,Order.class);
        User user = new User();
        user.setId(orderDto.getUser_id());
        List<Food> foodList = orderDto.getFoodList();
        int cost = foodList.stream().mapToInt(e -> e.getCost()).sum();

        order.setCreated_at(new Timestamp(System.currentTimeMillis()));
        order.setUser(user);
        order.setFoodList(foodList);
        order.setCost(cost);
        orderRepository.save(order);
        return order;
    }
}
