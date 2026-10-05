package com.nqd.qunhlanmakeupbe.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already exists", HttpStatus.CONFLICT),
    USER_NOT_FOUND(1003, "User not found", HttpStatus.NOT_FOUND),
    EMAIL_EXISTED(1004, "Email already exists", HttpStatus.CONFLICT),
    UNAUTHENTICATED(1005, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_REQUEST(1007, "Invalid request payload", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(1008, "Resource not found", HttpStatus.NOT_FOUND),
    INVALID_GOOGLE_TOKEN(1009, "Invalid Google authentication token", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED(1010, "User account is locked or deactivated", HttpStatus.FORBIDDEN);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
