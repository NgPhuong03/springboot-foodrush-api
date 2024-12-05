package com.foodrush.mobile_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
    private Double latitude;
    private Double longitude;

    public Location(Double a, Double b){
        this.latitude = a;
        this.longitude = b;
    }
}


