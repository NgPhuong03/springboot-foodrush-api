package com.foodrush.mobile_api.dto.response;

import com.foodrush.mobile_api.dto.request.FoodAddonRequest;
import com.foodrush.mobile_api.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long order_id;
    private String status;
    private Long cost;
    private Timestamp create_at;
    private Timestamp paid_at;
}
