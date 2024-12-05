package com.foodrush.mobile_api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Order order;

    @ManyToOne
    Food food;

    int quantity;

    @OneToMany(mappedBy = "orderFood", cascade = CascadeType.ALL)
    private Set<OrderFoodAddon> orderAddons;
}

