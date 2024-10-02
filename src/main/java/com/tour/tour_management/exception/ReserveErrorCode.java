package com.tour.tour_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ReserveErrorCode implements ErrorCode{
    RESERVE_NOTE_INVALID ("RESERVE_1","Note is invalid", HttpStatus.BAD_REQUEST),
    RESERVE_QUANTITY_NOT_ENOUGH ("RESERVE_2","Quantity dont enough", HttpStatus.BAD_REQUEST),
    ;

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ReserveErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
