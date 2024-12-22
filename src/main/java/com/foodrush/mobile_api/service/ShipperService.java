package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.response.Location;

public interface ShipperService {
    boolean updateLocation(Location location, Long shipper_id);

}
