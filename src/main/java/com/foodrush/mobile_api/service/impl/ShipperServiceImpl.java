package com.foodrush.mobile_api.service.impl;


import com.foodrush.mobile_api.dto.response.Location;
import com.foodrush.mobile_api.entity.Shipper;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.repository.ShipperRepository;
import com.foodrush.mobile_api.service.ShipperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShipperServiceImpl implements ShipperService {
    ShipperRepository shipperRepository;

    @Override
    public boolean updateLocation(Location location, Long shipper_id) {
        if (location.getLatitude() < -11 || location.getLatitude() > 11 || location.getLongitude() < -110 || location.getLongitude() > 110){
            throw new AppException(ErrorCode.WRONG_LOCATION_INFORMATION);
        }

        Shipper shipper = shipperRepository.findById(shipper_id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        shipper.setLatitude(location.getLatitude());
        shipper.setLongitude(location.getLongitude());
        shipperRepository.save(shipper);
        return true;
    }
}
