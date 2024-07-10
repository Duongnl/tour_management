package com.tour.tour_management.dto.response.employee;


import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.Date;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    Integer employee_id;
    String employee_name;
    Date birthday;
    BigInteger total_commission;
    BigInteger total_sales;
    int status;

}
