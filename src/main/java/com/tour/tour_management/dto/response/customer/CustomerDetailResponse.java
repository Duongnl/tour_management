package com.tour.tour_management.dto.response.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tour.tour_management.entity.Customer;
import com.tour.tour_management.entity.Reserve;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Builder // tao builder de tao doi tuong nhanh
@NoArgsConstructor
@AllArgsConstructor
// tu them private vao cac bien khai bao

public class CustomerDetailResponse {
    Integer customer_id;
    String customer_name;
    int sex;
    String relationship_name;
    String phone_number;
    String email;
    String address;
    Date birthday;
    Date visa_expire;
    LocalDateTime time;
    int status;
    CustomerResponse customerParent;
    Set<CustomerResponse> customerGroup;
}
