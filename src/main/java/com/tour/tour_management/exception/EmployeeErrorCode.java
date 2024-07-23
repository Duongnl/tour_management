package com.tour.tour_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum EmployeeErrorCode implements ErrorCode {
    EMPLOYEE_BIRTHDAY_INVALID("EMPLOYEE_1", "Year format, 1900, 2099" +
            "Month format, 1, 01, 2, 02… 12" +
            "Day format, 1, 01… 31" +
            "Leap year, February 29 days." +
            "Common year, February 28 days." +
            "31 Days of month – 1, 3, 5, 7, 8, 10, 12." +
            "30 Days of month – 4, 6, 9, 11,." +
            "ISO 8601, yyyy-MM-dd or uuuu-M-d, e.g 2020-11-03.", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NAME_INVALID("EMPLOYEE_2", "Name is invalid", HttpStatus.BAD_REQUEST),
    ;

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    EmployeeErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
