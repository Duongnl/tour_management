package com.tour.tour_management.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum TourTimeErrorCode {
    QUANTITY_ERROR ("TIME_1", "Quantity must be greater than total reserve and sale", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_MIN_INVALID("TIME_2", "Quantity  must be at least 0", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_SELL_MIN_INVALID("TIME_3", "Quantity sell  must be at least 0", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_RESERVE_MIN_INVALID("TIME_4", "Quantity reserve  must be at least 0", HttpStatus.BAD_REQUEST),
    TIME_PRICE_MIN_INVALID("TIME_5", "Price must be at least 0", HttpStatus.BAD_REQUEST),
    NUMBER_INVALID("TIME_9", "Data invalid",  HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_MAX_INVALID("TIME_10", "Quantity  cannot exceed 2100000000 ", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_SELL_MAX_INVALID("TIME_11", "Quantity sell cannot exceed 2100000000", HttpStatus.BAD_REQUEST),
    TIME_QUANTITY_RESERVE_MAX_INVALID("TIME_12", "Quantity reserve  cannot exceed 2100000000", HttpStatus.BAD_REQUEST),
    TIME_PRICE_MAX_INVALID("TIME_13", "Price cannot exceed 2100000000", HttpStatus.BAD_REQUEST)
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
