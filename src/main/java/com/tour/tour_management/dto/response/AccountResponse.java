package com.tour.tour_management.dto.response;


import com.tour.tour_management.entity.Employee;
import com.tour.tour_management.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {

    String account_name;
    String email;
    int phone_number;
    Role role;
    Employee employee;

}
