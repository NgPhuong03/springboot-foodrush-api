package com.foodrush.mobile_api.dto;

import com.foodrush.mobile_api.entity.Food;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String note;
    private boolean paymethod;
    private Long user_id;
    private List<Food> foodList;
    private String status;
}
