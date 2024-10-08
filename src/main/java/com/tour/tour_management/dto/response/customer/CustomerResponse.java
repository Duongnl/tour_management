package com.tour.tour_management.dto.response.customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@Builder // tao builder de tao doi tuong nhanh
@NoArgsConstructor
@AllArgsConstructor
// tu them private vao cac bien khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerResponse {
    Integer customer_id;
    String customer_name;
    int sex;
    String relationship_name;
    String phone_number;
    String email;
    String address;
    LocalDate birthday;
    LocalDate visa_expire;
    LocalDateTime time;
    int status;
}
