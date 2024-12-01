package com.foodrush.mobile_api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderFood {
    @EmbeddedId
    OrderFoodId id;

    @ManyToOne
    @MapsId("orderId")
    Order order;

    @ManyToOne
    @MapsId("foodId")
    Food food;

    int quantity;
}

