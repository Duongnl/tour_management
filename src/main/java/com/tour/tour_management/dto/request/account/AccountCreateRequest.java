package com.tour.tour_management.dto.request.account;


import com.tour.tour_management.dto.request.employee.EmployeeCreateRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequest {

    String account_name;
    String password;
    String email;
    String phone_number;
    Integer role_id;
    ZonedDateTime time;

    EmployeeCreateRequest employee;


}
