package com.tour.tour_management.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

// chứa lỗi và mã lỗi
@Getter
public enum CategoryErrorCode implements ErrorCode {
    CATEGORY_NOT_FOUND("CATEGORY_1", "Category not found", HttpStatus.NOT_FOUND),
    ;

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    CategoryErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}