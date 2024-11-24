package com.foodrush.mobile_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class Username extends RuntimeException {
    public Username(String message) {
        super(message);
    }
}
