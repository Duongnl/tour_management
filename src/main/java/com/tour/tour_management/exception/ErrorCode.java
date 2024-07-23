package com.tour.tour_management.exception;

import org.springframework.http.HttpStatusCode;

public interface ErrorCode {


    public String getCode();

    public String getMessage();

    public HttpStatusCode getHttpStatusCode();
}
