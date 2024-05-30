package com.tour.tour_management.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

// chứa lỗi và mã lỗi
@Getter
public enum TourErrorCode implements ErrorCode {
    TOUR_NOT_FOUND("TOUR_1", "Tour not found", HttpStatus.NOT_FOUND),
    TOUR_NAME_NOT_BLANK("TOUR_2", "Tour name not blank", HttpStatus.BAD_REQUEST),
    TOUR_CATEGORY_ID_NOT_BLANK("TOUR_3", "Category id not blank", HttpStatus.BAD_REQUEST),
    QUANTITY_ERROR ("TOUR_4", "Quantity must be greater than total reserve and sale", HttpStatus.BAD_REQUEST),
    TOUR_QUANTITY_INVALID("TOUR_5", "Quantity  must be at least 0", HttpStatus.BAD_REQUEST),
    TOUR_QUANTITY_SELL_INVALID("TOUR_6", "Quantity sell  must be at least 0", HttpStatus.BAD_REQUEST),
    TOUR_QUANTITY_RESERVE_INVALID("TOUR_7", "Quantity reserve  must be at least 0", HttpStatus.BAD_REQUEST),
    TOUR_PRICE_INVALID("TOUR_8", "Price must be at least 0", HttpStatus.BAD_REQUEST),
    NUMBER_INVALID("TOUR_9", "Data must be number",  HttpStatus.BAD_REQUEST)
    ;

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    TourErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
