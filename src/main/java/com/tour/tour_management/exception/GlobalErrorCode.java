package com.tour.tour_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum GlobalErrorCode implements ErrorCode {

    GLOBAL_EMAIL_INVALID ("GLOBAL_1", "- local-part :" +
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
    ACCOUNT_PHONE_NUMBER_INVALID("GLOBAL_2", "Phone number is invalid", HttpStatus.BAD_REQUEST)

    ;
    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    GlobalErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
