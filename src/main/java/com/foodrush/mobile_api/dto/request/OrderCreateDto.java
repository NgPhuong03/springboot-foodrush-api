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
    private Long user_id;
    private Long address_id;
    private String note;
    private boolean paymethod;
    private Long cost;
    private List<FoodAddonRequest> list;
}
