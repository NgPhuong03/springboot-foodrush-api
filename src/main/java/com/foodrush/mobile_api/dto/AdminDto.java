package com.foodrush.mobile_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long id;
    private String name;
    private String email;
    private String phone_number;
}
