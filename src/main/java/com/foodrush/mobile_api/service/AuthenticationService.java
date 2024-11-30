package com.foodrush.mobile_api.service;

import com.foodrush.mobile_api.dto.request.AuthenticationRequest;
import com.foodrush.mobile_api.dto.response.AuthenticationResponse;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.exception.ResourceNotFoundException;
import com.foodrush.mobile_api.repository.AdminRepository;
import com.foodrush.mobile_api.repository.ShipperRepository;
import com.foodrush.mobile_api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.BadCredentialsException;
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
        Long id ;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        var user = userRepository.findByEmail(request.getEmail());
        var admin = adminRepository.findByEmail(request.getEmail());
        var shipper = shipperRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            id = user.get().getId();
            role = "user";
            isAu = passwordEncoder.matches( request.getPassword(), user.get().getPassword());
        }else if (admin.isPresent()) {
            id = admin.get().getId();
            role = "admin";
            isAu = passwordEncoder.matches( request.getPassword(), admin.get().getPassword());
        } else if (shipper.isPresent()){
            id = shipper.get().getId();
            role = "shipper";
            isAu = passwordEncoder.matches( request.getPassword(), shipper.get().getPassword());
        }        else
            throw new AppException(ErrorCode.WRONG_LOGIN_INFORMATION);

        if (!isAu)
            throw new AppException(ErrorCode.WRONG_LOGIN_INFORMATION);
        return AuthenticationResponse.builder()
                .authenticated(isAu)
                .role(role)
                .id(id)
                .build();
    }
}
