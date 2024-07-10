package com.tour.tour_management.dto.response.account;


import com.tour.tour_management.dto.response.RoleResponse;
import com.tour.tour_management.dto.response.employee.EmployeeResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {

    String account_id;
    String account_name;
    String email;
    ZonedDateTime time;
    int status;
    RoleResponse role;

}
