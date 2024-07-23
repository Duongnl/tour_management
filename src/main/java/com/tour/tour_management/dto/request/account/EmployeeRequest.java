package com.tour.tour_management.dto.request.account;


import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.Date;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {

    @Pattern(regexp = "^[\\p{L} ]{2,255}$",message = "EMPLOYEE_NAME_INVALID")
    String employee_name;

//    @Pattern(regexp = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])",message = "EMPLOYEE_BIRTHDAY_INVALID")
    Date birthday;

    BigInteger total_commission;

    BigInteger total_sales;
}
