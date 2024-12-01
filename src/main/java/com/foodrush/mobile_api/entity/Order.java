package com.foodrush.mobile_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500,columnDefinition = "TEXT")
    private String note;
    private boolean paymethod;
    private String status;
    private Long cost;
    private Timestamp created_at;
    private Timestamp paid_at;
    private Long address_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderFood> orderFoods;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderAddon> orderAddons;
}
