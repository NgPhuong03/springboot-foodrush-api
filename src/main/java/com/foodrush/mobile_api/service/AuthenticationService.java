package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.request.AuthenticationRequest;
import com.foodrush.mobile_api.dto.response.AuthenticationResponse;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.exception.Unauthenticated;
import com.foodrush.mobile_api.repository.AdminRepository;
import com.foodrush.mobile_api.repository.ShipperRepository;
import com.foodrush.mobile_api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    AdminRepository adminRepository;
    ShipperRepository shipperRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var role = "";
        boolean isAu = false;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        var user = userRepository.findByUsername(request.getUsername());
        var admin = adminRepository.findByUsername(request.getUsername());
        var shipper = shipperRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            role = "user";
            isAu = passwordEncoder.matches( request.getPassword(), user.get().getPassword());
        }else if (admin.isPresent()) {
            role = "admin";
            isAu = passwordEncoder.matches( request.getPassword(), admin.get().getPassword());
        } else if (shipper.isPresent()){
            role = "shipper";
            isAu = passwordEncoder.matches( request.getPassword(), shipper.get().getPassword());
        } else
            throw new ResourceNotFoundException("Khong tim thay username: " + request.getUsername());

        return AuthenticationResponse.builder()
                .authenticated(isAu)
                .role(role)
                .build();
    }
}
