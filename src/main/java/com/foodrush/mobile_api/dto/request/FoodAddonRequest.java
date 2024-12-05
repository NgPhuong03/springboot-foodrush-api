package com.foodrush.mobile_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodAddonRequest {
    private Long food_id;
    private int food_quantity;
    private List<AddOnList> addon;
}



