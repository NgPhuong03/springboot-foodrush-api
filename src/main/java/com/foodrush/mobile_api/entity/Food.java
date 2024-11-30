package com.foodrush.mobile_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT", length = 500)
    private String image;
    @Column(columnDefinition = "TEXT")
    private String description;
    private int sale;
    private int cost;
    private boolean rice;
    private boolean noodle;
    private boolean mon40k;
    private boolean vegan;
}
