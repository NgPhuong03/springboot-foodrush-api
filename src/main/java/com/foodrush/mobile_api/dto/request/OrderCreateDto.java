package com.foodrush.mobile_api.dto.request;


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
    private List<FoodQuantity> foods;
    private List<AddonQuantity> addons;
    private Long cost;
    private Long address_id;
}
