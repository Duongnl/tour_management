package com.tour.tour_management.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum TourTimeErrorCode implements ErrorCode {
    QUANTITY_ERROR ("TOURTIME_1", "Quantity must be greater than total reserve and sale", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_MIN_INVALID("TOURTIME_2", "Quantity  must be at least 0", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_SELL_MIN_INVALID("TOURTIME_3", "Quantity sell  must be at least 0", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_RESERVE_MIN_INVALID("TOURTIME_4", "Quantity reserve  must be at least 0", HttpStatus.BAD_REQUEST),
    TIME_PRICE_MIN_INVALID("TOURTIME_5", "Price must be at least 0", HttpStatus.BAD_REQUEST),
    NUMBER_INVALID("TOURTIME_6", "Data invalid",  HttpStatus.BAD_REQUEST),
    TIME_NAME_NOT_BLANK("TOURTIME_7", "Tour Time not blank",HttpStatus.BAD_REQUEST),
    TIME_NAME_INVALID("TOURTIME_8", "Tour Time name invalid",HttpStatus.BAD_REQUEST),
    TIME_DEPARTURE_TIME_INVALID("TOURTIME_9", "Tour Time departure time invalid",HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_MAX_INVALID("TOURTIME_10", "Quantity  cannot exceed 2100000000 ", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_SELL_MAX_INVALID("TOURTIME_11", "Quantity sell cannot exceed 2100000000", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_RESERVE_MAX_INVALID("TOURTIME_12", "Quantity reserve  cannot exceed 2100000000", HttpStatus.BAD_REQUEST),
    TIME_PRICE_MAX_INVALID("TOURTIME_13", "Price cannot exceed 2100000000", HttpStatus.BAD_REQUEST),
    TIME_NOT_FOUND("TOURTIME_14", "Tour Time not found",HttpStatus.NOT_FOUND),
    TIME_RETURN_TIME_INVALID("TOURTIME_15", "Tour Time return time invalid",HttpStatus.BAD_REQUEST),
    TIME_DEPARTURE_DATE_INVALID("TOURTIME_16", "Tour Time departure date invalid",HttpStatus.BAD_REQUEST),
    TIME_RETURN_DATE_INVALID("TOURTIME_17", "Tour Time return date invalid",HttpStatus.BAD_REQUEST),
    TIME_VISA_EXPIRE_INVALID("TOURTIME_18", "Tour Time visa expire invalid",HttpStatus.BAD_REQUEST),
    ;
    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    TourTimeErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
