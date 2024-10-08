package com.tour.tour_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum RoleErrorCode implements ErrorCode {
    ROLE_NOT_FOUND("ROLE_1", "Role not found", HttpStatus.NOT_FOUND),
    ROLE_NAME_INVALID("ROLE_2", "Role is invalid", HttpStatus.BAD_REQUEST),
    ROLE_ACCOUNT_USE("ROLE_3", "Role has an account in use", HttpStatus.BAD_REQUEST)
    ;

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    RoleErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
