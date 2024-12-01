package com.foodrush.mobile_api.dto.response;

import com.foodrush.mobile_api.dto.FoodDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeScreenFood {
    List<FoodDto> topSale;
    List<FoodDto> topRating;
    List<FoodDto> topOrder;
}
