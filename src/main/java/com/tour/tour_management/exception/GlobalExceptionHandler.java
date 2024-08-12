package com.tour.tour_management.exception;


import com.tour.tour_management.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
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
        List<ApiResponse> apiResponseList = new ArrayList<>();
        ErrorCode errorCode = appException.getErrorCode();
        apiResponseList.add(ApiResponse.builder()
                .status(null)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .status("FAIL")
                        .error(apiResponseList)
                        .build());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse> handlingHttpMessage(HttpMessageNotReadableException exception){
        List<ApiResponse> apiResponseList = new ArrayList<>();
        apiResponseList.add(ApiResponse.builder()
                .status(null)
                .code(TourTimeErrorCode.NUMBER_INVALID.getCode())
                .message(TourTimeErrorCode.NUMBER_INVALID.getMessage())
                .build());

        return  ResponseEntity.badRequest().body(ApiResponse.builder()
                .status("FAIL")
                        .error(apiResponseList)
                .build()
        );
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception){
        List<ApiResponse> apiResponseList = new ArrayList<>();
        apiResponseList.add(ApiResponse.builder()
                .status(null)
                .code(PermissionErrorCode.UNAUTHORIZED.getCode())
                .message(PermissionErrorCode.UNAUTHORIZED.getMessage())
                .build());

        return  ResponseEntity.badRequest().body(ApiResponse.builder()
                .status("FAIL")
                .error(apiResponseList)
                .build()
        );
    }


//    DefaultHandlerExceptionResolver
    @ExceptionHandler( value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){

        List<ApiResponse> apiResponseList = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(exp -> {
            ErrorCode errorCode = handlingErrorCode(exp.getDefaultMessage());
            apiResponseList.add(ApiResponse.builder()
                    .status(null)
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build());
        });

        return  ResponseEntity.badRequest().body(ApiResponse.builder()
                .status("FAIL")
                .error(apiResponseList)
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
            case "AIRLINE" :
                return  AirlineErrorCode.valueOf(enumKey);
            case "ROLE" :
                return  RoleErrorCode.valueOf(enumKey);
            case "ACCOUNT" :
                return  AccountErrorCode.valueOf(enumKey);
            case "EMPLOYEE" :
                return  EmployeeErrorCode.valueOf(enumKey);
            case "PERMISSION" :
                return  PermissionErrorCode.valueOf(enumKey);
            default:
                return null;
        }
    }


}
