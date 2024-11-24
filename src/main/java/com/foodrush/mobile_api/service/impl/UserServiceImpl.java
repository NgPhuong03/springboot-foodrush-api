package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.OrderDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.entity.Order;
import com.foodrush.mobile_api.entity.User;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.UserRepository;
import com.foodrush.mobile_api.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(User user) {
        userRepository.save(user);
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Khong tim thay nguoi dung id: " + id));
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User lastUser = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Khong tim thay user id: " + id));
        lastUser.setName(userDto.getName());
        lastUser.setEmail(userDto.getEmail());
        lastUser.setPhone_number(userDto.getPhone_number());
        return modelMapper.map(userRepository.save(lastUser), UserDto.class);
    }

    @Override
    public List<OrderDto> getOrders(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Khong tim thay user id: " + id));
        List<OrderDto> orderDtoList = user.getOrderList().stream().map(e -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(e.getId());
            orderDto.setNote(e.getNote());
            orderDto.setFoodList(e.getFoodList());
            orderDto.setUser_id(e.getUser().getId());
            orderDto.setStatus(e.getStatus());
            return orderDto;
        }).toList();
        return orderDtoList;
    }


}
