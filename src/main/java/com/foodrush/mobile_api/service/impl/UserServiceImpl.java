package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.dto.response.AddressDto;
import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.dto.response.UserCreatedResponse;
import com.foodrush.mobile_api.entity.Food;
import com.foodrush.mobile_api.entity.User;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.FoodRepository;
import com.foodrush.mobile_api.repository.UserRepository;
import com.foodrush.mobile_api.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private FoodRepository foodRepository;
    private FoodServiceImpl foodService;
    private ModelMapper modelMapper;

    @Override
    public UserCreatedResponse createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

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

            orderResponseDto.setOrder_id(e.getId());
            orderResponseDto.setStatus(e.getStatus());
            orderResponseDto.setCost(e.getCost());
            orderResponseDto.setCreate_at(e.getCreated_at());
            orderResponseDto.setPaid_at(e.getPaid_at());
            return orderResponseDto;
        }).toList();

        return orderResponseDtoList;
    }

    @Override
    public List<AddressDto> getAddress(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay user id: " + id));

        return user.getAddresses().stream().map((e) -> {
            AddressDto dto = new AddressDto();
            dto.setAddress(e.getAddress());
            dto.setLatitude(e.getLatitude());
            dto.setLongitude(e.getLongitude());
            dto.setDistance(e.getDistance());
            dto.setType(e.getType());
            dto.setTitle(e.getTitle());
            dto.setId(e.getId());
            return dto;
        }).toList();
    }

    @Override
    public void addFavoriteFood(Long id, Long food_id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Food food = foodRepository.findById(food_id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Set<Food> foods = user.getFavoriteFood();
        foods.add(food);
        user.setFavoriteFood(foods);
        userRepository.save(user);
    }

    @Override
    public void deleteFavoriteFood(Long user_id, Long food_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            Food food = foodRepository.findById(food_id).orElseThrow(() -> new  AppException(ErrorCode.INVALID_KEY));
            user.getFavoriteFood().remove(food);
            userRepository.save(user);
        }
    }

    @Override
    public List<FoodDto> getFavorites(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Set<Food> foods = user.getFavoriteFood();

        List<FoodDto> foodDtos = foods.stream()
                .map(e -> foodService.getFood(e.getId()))
                .collect(Collectors.toList());

        return foodDtos;
    }


}
