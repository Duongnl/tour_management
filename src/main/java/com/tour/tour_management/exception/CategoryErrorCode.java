package com.tour.tour_management.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

// chứa lỗi và mã lỗi
@Getter
public enum CategoryErrorCode implements ErrorCode {
    CATEGORY_NOT_FOUND("CATEGORY_1", "Category not found", HttpStatus.NOT_FOUND),
    CATEGORY_NAME_NOT_BLANK ("CATEGORY_2","Category name not blank", HttpStatus.BAD_REQUEST),
    CATEGORY_URL_NOT_BLANK ("CATEGORY_3","Category url not blank", HttpStatus.BAD_REQUEST)

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
