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
    ACCOUNT_LOCKED ("ACCOUNT_6", "Account is locked ", HttpStatus.BAD_REQUEST),
    ACCOUNT_EMAIL_INVALID ("ACCOUNT_7", "- local-part :" +
            "uppercase and lowercase Latin letters A to Z and a to z." +
            "digits 0 to 9." +
            "Allow dot (.), underscore (_) and hyphen (-)." +
            "dot (.) is not the first or last character." +
            "dot (.) does not appear consecutively, e.g. mkyong..yong@example.com is not allowed.\n" +
            "Max 64 characters." +
            "- domain :" +
            "uppercase and lowercase Latin letters A to Z and a to z." +
            "digits 0 to 9." +
            "hyphen (-) is not the first or last character." +
            "dot (.) is not the first or last character." +
            "dot (.) does not appear consecutively." +
            "tld min 2 characters.", HttpStatus.BAD_REQUEST),
    ACCOUNT_PHONE_NUMBER_INVALID("ACCOUNT_8", "Phone number is invalid", HttpStatus.BAD_REQUEST),

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
