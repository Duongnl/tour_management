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
    CUSTOMER_RELATIONSHIP_ID_NOT_FOUND("CUSTOMER_5", "Customer relationship not found", HttpStatus.BAD_REQUEST),
    CUSTOMER_LOCKED("CUSTOMER_6", "Customer is locked", HttpStatus.BAD_REQUEST),
    CUSTOMER_SEX_INVALID("CUSTOMER_7", "Customer sex invalid", HttpStatus.BAD_REQUEST),
    CUSTOMER_PHONE_NUMBER_INVALID("CUSTOMER_8", "Customer phone invalid", HttpStatus.BAD_REQUEST),
    CUSTOMER_EMAIL_INVALID("CUSTOMER_9", "Customer email invalid", HttpStatus.BAD_REQUEST),
    CUSTOMER_RELATIONSHIP_NAME_INVALID("CUSTOMER_10", "Customer relationship name invalid", HttpStatus.BAD_REQUEST),
    CUSTOMER_ADDRESS_INVALID("CUSTOMER_11", "Customer address invalid", HttpStatus.BAD_REQUEST),
    CUSTOMER_BIRTHDAY_INVALID("CUSTOMER_12", "Customer birthday invalid", HttpStatus.BAD_REQUEST),
    CUSTOMER_VISA_EXPIRE_INVALID("CUSTOMER_13", "Customer visa_expire invalid", HttpStatus.BAD_REQUEST),
    CUSTOMER_NAME_INVALID("CUSTOMER_14", "Customer name invalid", HttpStatus.BAD_REQUEST)
    ;


    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    CustomerErrorCode(String code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
