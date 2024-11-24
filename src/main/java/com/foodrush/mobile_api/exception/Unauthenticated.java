package com.foodrush.mobile_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Unauthenticated extends RuntimeException {
    public Unauthenticated(String message) {
        super(message);
    }
}
