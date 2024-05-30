package com.tour.tour_management.exception;


import com.tour.tour_management.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

// tiep nhan exception va xu ly chung
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException (AppException appException){
        ErrorCode errorCode = appException.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code("FAIL")
                        .result(ApiResponse.builder()
                                .code(errorCode.getCode())
                                .message(errorCode.getMessage())
                                .build())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){

        List<ApiResponse> apiResponseList = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(exp -> {
            ErrorCode errorCode = handlingErrorCode(exp.getDefaultMessage());
            apiResponseList.add(ApiResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build());
        });

        //        Lấy ra mã enum của ErrorCode enumKey = TOUR_NAME_NOT_NULL
//        String enumKey = exception.getFieldError().getDefaultMessage();
//        lay ra doi tuong enum errorCode bang mã enum

        return  ResponseEntity.badRequest().body(ApiResponse.builder()
                .code("FAIL")
                .result(apiResponseList)
                .build()
        );
    }

    public ErrorCode handlingErrorCode(String enumKey ) {
        String[] parts = enumKey.split("_");
        String errorClassName = parts[0];
        switch (errorClassName){
            case "TOUR" :
                return TourErrorCode.valueOf(enumKey);
            case "CATEGORY" :
                return CategoryErrorCode.valueOf(enumKey);
            default:
                return null;
        }
    }


}
