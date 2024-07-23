package com.tour.tour_management.dto.request.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder // tao builder de tao doi tuong nhanh
@NoArgsConstructor
@AllArgsConstructor
// tu them private vao cac bien khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerCreateRequest {
    @NotBlank(message = "CUSTOMER_NAME_NOT_BLANK")
    String customer_name;
    int sex;
    Integer customer_rel_id;
    String phone_number;
    String email;
    String relationship_name;
    String address;
    Date birthday;
    Date visa_expire;
}
