package com.foodrush.mobile_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FoodQuantity {
    private Long food_id;
    private int quantity;
}
