package com.tour.tour_management.dto.response.reserve;

import com.tour.tour_management.dto.response.account.EmployeeResponse;
import com.tour.tour_management.dto.response.customer.CustomerResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveResponse {
    String reserve_id;
    String note;
    int price;
    int commission;
    LocalDateTime time;
    int status;

    EmployeeResponse employee;

    CustomerResponse customer;


    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = time.format(formatter);
        return formattedTime;
    }
}
