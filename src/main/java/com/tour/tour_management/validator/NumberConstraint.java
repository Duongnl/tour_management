package com.tour.tour_management.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // chi validation cho 1 bien
@Retention(RetentionPolicy.RUNTIME) // annotaion dc xu ly khi runtime
@Constraint(validatedBy = {NumberValidator.class})
// lop nay moi chi la khai bao thoi
public @interface NumberConstraint {

    String message() default "Invalid number";

    //    khai bao gia tri toi thieu la bao nhieu
    int min();


    //    nay mac dinh cua annotation
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
