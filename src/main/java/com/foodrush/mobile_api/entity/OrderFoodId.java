package com.foodrush.mobile_api.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class OrderFoodId implements Serializable {
    private Long foodId;
    private Long orderId;
}
