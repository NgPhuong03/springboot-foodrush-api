package com.foodrush.mobile_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddOnList {
    private Long addon_id;
    private int addon_quantity;
}
