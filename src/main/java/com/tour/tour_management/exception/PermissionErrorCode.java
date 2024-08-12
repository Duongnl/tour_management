package com.tour.tour_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum PermissionErrorCode implements ErrorCode {
    PERMISSION_NOT_FOUND("PERMISSION_1", "Permission not found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("PERMISSION_2","You do not have permission", HttpStatus.FORBIDDEN),

    ;

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    PermissionErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
