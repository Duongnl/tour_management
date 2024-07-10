package com.tour.tour_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum AccountErrorCode implements ErrorCode{
    ACCOUNT_EXISTED("ACCOUNT_1", "Account existed", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND("ACCOUNT_2", "Account not found", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED("ACCOUNT_3","Password is wrong", HttpStatus.UNAUTHORIZED),
    ACCOUNT_NAME_INVALID("ACCOUNT_4", "Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase." +
            "Username allowed of the dot (.), underscore (_), and hyphen (-)." +
            "The dot (.), underscore (_), or hyphen (-) must not be the first or last character." +
            "The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex" +
            "The number of characters must be between 5 to 20.", HttpStatus.BAD_REQUEST),
    ACCOUNT_PASSWORD_INVALID("ACCOUNT_5", "Password must contain at least one digit [0-9]." +
            "Password must contain at least one lowercase Latin character [a-z]." +
            "Password must contain at least one uppercase Latin character [A-Z]." +
            "Password must contain at least one special character like ! @ # & ( )." +
            "Password must contain a length of at least 8 characters and a maximum of 20 characters.", HttpStatus.BAD_REQUEST),
    ;

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    AccountErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
