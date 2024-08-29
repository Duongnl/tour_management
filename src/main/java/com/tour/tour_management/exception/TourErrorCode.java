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
    TOUR_TOURTIME_NOT_FOUND("TOUR_4","Tour Time not found",HttpStatus.NOT_FOUND)
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
