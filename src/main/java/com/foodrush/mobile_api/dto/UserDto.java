package com.foodrush.mobile_api.dto;

import com.foodrush.mobile_api.dto.response.OrderResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone_number;
    private List<OrderResponseDto> orderResponseDtoList;
}
