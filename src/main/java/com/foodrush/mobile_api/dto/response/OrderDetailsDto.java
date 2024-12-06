package com.foodrush.mobile_api.dto.response;

import com.foodrush.mobile_api.dto.request.FoodAddonRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    private Long user_id;
    private Long shipper_id;
    private String status;
    private Timestamp create_at;
    private Timestamp paid_at;
    private String note;
    private boolean paymethod;
    private Long cost;
    private AddressDto address;
    private List<FoodAddOnResponse> list;

}
