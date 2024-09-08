package com.tour.tour_management.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class Regex {
    public static final String REGEX_EMAIL= "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static final String REGEX_PHONE_NUMBER= "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
}