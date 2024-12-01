package com.foodrush.mobile_api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderAddon {
    @EmbeddedId
    OrderAddonId id;

    @ManyToOne
    @MapsId("orderId")
    Order order;

    @ManyToOne
    @MapsId("addonId")
    AddOn addOn;

    int quantity;
}
