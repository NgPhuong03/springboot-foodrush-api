package com.foodrush.mobile_api.dto.response;

import com.foodrush.mobile_api.dto.FoodDto;
import com.foodrush.mobile_api.entity.AddOn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    Long cart_id;
    FoodDto food;
    int food_quantity;
    List<AddonDto> addonList;
}
