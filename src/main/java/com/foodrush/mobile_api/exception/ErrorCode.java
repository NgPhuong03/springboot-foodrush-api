package com.foodrush.mobile_api.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Lỗi không xác định"),
    INVALID_KEY(1404, "Validation key error"),
    USER_EXISTED(1001, "Người dùng đã tồn tại"),
    UNAUTHORIZED(1002, "Chưa đăng nhập"),
    WRONG_LOGIN_INFORMATION(1003, "Tài khoản hoặc mật khẩu không chính xác")
    ;


    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;
}
