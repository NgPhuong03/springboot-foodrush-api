package com.foodrush.mobile_api.dto.response;

import com.foodrush.mobile_api.dto.UserDto;
import com.foodrush.mobile_api.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    Long id;
    String role;
    boolean authenticated;
}
