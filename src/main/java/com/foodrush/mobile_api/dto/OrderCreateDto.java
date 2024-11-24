package com.foodrush.mobile_api.dto;

import com.foodrush.mobile_api.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {
    private String note;
    private boolean paymethod;
    private Long user_id;
    private List<Long> foodList;
    private String status;
    private int cost;
}
