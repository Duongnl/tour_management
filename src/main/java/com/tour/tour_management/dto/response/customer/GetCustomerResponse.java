package com.tour.tour_management.dto.response.customer;

import jakarta.persistence.criteria.CriteriaBuilder;
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

public class GetCustomerResponse {

    Integer customer_id;
    String customer_name;
    int sex;
    Integer customer_rel_id;
    String phone_number;
    String email;
    String relationship_name;
    String address;
    Date birthday;
    Date visa_expire;
    int status;
    LocalDateTime time;


    public String getUrl(){return customer_name+'-'+customer_id;}
}
