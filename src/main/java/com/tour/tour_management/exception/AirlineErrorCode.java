package com.tour.tour_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum AirlineErrorCode implements ErrorCode {
      AIRLINE_NAME_NOT_BLANK("AIRLINE_1", "Airline name not blank", HttpStatus.BAD_REQUEST),
      AIRLINE_DEPARTURE_NOT_BLANK("AIRLINE_2", "Departure time is not blank", HttpStatus.BAD_REQUEST),
      AIRLINE_RETURN_NOT_BLANK ("AIRLINE_3", "Return time is not blank", HttpStatus.BAD_REQUEST),
      AIRLINE_NOT_FOUND ("AIRLINE_4", "Airline is not found", HttpStatus.NOT_FOUND),
      AIRLINE_DEPARTURE_RETURN ("AIRLINE_5", "Departure time must be before return time",HttpStatus.BAD_REQUEST),
    ;
    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    AirlineErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
