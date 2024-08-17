package com.tour.tour_management.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

// chứa lỗi và mã lỗi
@Getter
public enum CustomerErrorCode implements ErrorCode {
    CUSTOMER_NOT_FOUND("CUSTOMER_1", "Customer not found", HttpStatus.NOT_FOUND),
    CUSTOMER_NAME_NOT_BLANK ("CUSTOMER_2","Customer name not blank", HttpStatus.BAD_REQUEST),
    CUSTOMER_URL_NOT_BLANK ("CUSTOMER_3","Customer url not blank", HttpStatus.BAD_REQUEST),
    CUSTOMER_PHONE_NUMBER_EXISTED("CUSTOMER_4", "Customer phone number existed", HttpStatus.BAD_REQUEST),
    CUSTOMER_RELATIONSHIP_ID_DIDNT_EXIST("CUSTOMER_5", "Customer relationship not found", HttpStatus.BAD_REQUEST);


    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    CustomerErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
