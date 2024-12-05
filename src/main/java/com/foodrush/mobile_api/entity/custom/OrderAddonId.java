package com.foodrush.mobile_api.entity.custom;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddonId implements Serializable {
    private Long addonId;
    private Long orderFoodId;
}
