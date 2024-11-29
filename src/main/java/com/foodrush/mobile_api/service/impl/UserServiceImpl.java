package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.dto.response.UserCreatedResponse;
import com.foodrush.mobile_api.entity.Address;
import com.foodrush.mobile_api.entity.User;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.exception.Username;
import com.foodrush.mobile_api.repository.AdminRepository;
import com.foodrush.mobile_api.repository.ShipperRepository;
import com.foodrush.mobile_api.repository.UserRepository;
import com.foodrush.mobile_api.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private ShipperRepository shipperRepository;
    private ModelMapper modelMapper;

    @Override
    public UserCreatedResponse createUser(User user) {
        if(adminRepository.findByEmail(user.getEmail()).isPresent()
                || adminRepository.findByEmail(user.getEmail()).isPresent()
                || shipperRepository.findByEmail(user.getEmail()).isPresent()
        )
            throw new Username("Tai khoan da co nguoi su dung");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        UserCreatedResponse userCreated = modelMapper.map(user,UserCreatedResponse.class);
        return userCreated;
    }

    @Override
    public UserCreatedResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Khong tim thay nguoi dung id: " + id));
        UserCreatedResponse userGot = modelMapper.map(user,UserCreatedResponse.class);
        return userGot;
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
    public List<OrderResponseDto> getOrders(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Khong tim thay user id: " + id));

        List<OrderResponseDto> orderResponseDtoList = user.getOrderList().stream().map(e -> {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setNote(e.getNote());
            orderResponseDto.setFoodList(e.getFoodList());
            orderResponseDto.setUser_id(e.getUser().getId());
            orderResponseDto.setStatus(e.getStatus());
            orderResponseDto.setCost(e.getCost());
            return orderResponseDto;
        }).toList();

        return orderResponseDtoList;
    }

    @Override
    public List<Address> getAddress(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay user id: " + id));

        return user.getAddresses();
    }


}
