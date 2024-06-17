package com.tour.tour_management.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String employee_id;
    String employee_name;
    Date birthday;
    int commission;
    int status;

}
