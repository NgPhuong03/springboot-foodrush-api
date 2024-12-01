package com.foodrush.mobile_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddOn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int price;
    String name;

    @OneToMany(mappedBy = "addOn", cascade = CascadeType.ALL)
    private Set<OrderAddon> orderAddons;
}
