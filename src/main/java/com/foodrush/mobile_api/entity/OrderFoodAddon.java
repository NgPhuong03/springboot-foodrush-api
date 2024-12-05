package com.foodrush.mobile_api.entity;

import com.foodrush.mobile_api.entity.custom.OrderAddonId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderFoodAddon {
    @EmbeddedId
    OrderAddonId id;

    @ManyToOne
    @MapsId("orderFoodId")
    OrderFood order;

    @ManyToOne
    @MapsId("addonId")
    AddOn addOn;

    int quantity;
}
