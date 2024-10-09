package com.tour.tour_management.dto.request.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder // tao builder de tao doi tuong nhanh
@NoArgsConstructor
@AllArgsConstructor
// tu them private vao cac bien khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerRequest {
    @Pattern(regexp = "^(?=(.*\\p{L}){2,})[\\p{L} ]{2,255}",message ="CUSTOMER_NAME_INVALID" )
    @NotBlank(message = "CUSTOMER_NAME_NOT_BLANK")
    String customer_name;

    @NotNull(message = "CUSTOMER_SEX_INVALID")
    int sex;

    Integer customer_rel_id;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$",message = "CUSTOMER_PHONE_NUMBER_ERROR_FORMAT")
    @NotNull(message = "CUSTOMER_PHONE_NUMBER_INVALID")
    String phone_number;

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" ,message = "CUSTOMER_EMAIL_INVALID")
    @NotNull(message = "CUSTOMER_EMAIL_INVALID")
    String email;

    @Pattern(regexp = "^(?=(.*\\p{L}){2,})[\\p{L} ]{2,255}",message = "CUSTOMER_RELATIONSHIP_NAME_INVALID")
    String relationship_name;

    @Pattern(regexp = "^[\\p{L}0-9 ,.\\-]{0,255}$",message = "CUSTOMER_ADDRESS_INVALID")
    @NotNull(message = "CUSTOMER_ADDRESS_INVALID")
    String address;

    @NotNull(message = "CUSTOMER_BIRTHDAY_INVALID")
    LocalDate birthday;

    @NotNull(message = "CUSTOMER_VISA_EXPIRE_INVALID")
    LocalDate visa_expire;
}
