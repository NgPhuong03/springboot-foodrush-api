package com.foodrush.mobile_api.dto.response;

import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.dto.request.AddOnList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodAddOnResponse {
    private FoodDto food;
    private int food_quantity;
    private List<AddonDto> addon_list;
}
