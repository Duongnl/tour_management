package com.tour.tour_management.dto.response.account;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
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


    public String getBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedBirthday = sdf.format(this.birthday);
        return formattedBirthday;
    }


}
