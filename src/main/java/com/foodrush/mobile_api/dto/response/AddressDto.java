package com.foodrush.mobile_api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private Double latitude;
    private Double longitude;
    private Float distance;
    private String title;
    private String type;
}
