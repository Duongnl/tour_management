package com.tour.tour_management.dto.request.account;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",message = "ACCOUNT_NAME_INVALID")
    @NotNull(message = "ACCOUNT_NAME_INVALID")
    String account_name;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!.@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "ACCOUNT_PASSWORD_INVALID")
    String password;


    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message = "ACCOUNT_EMAIL_INVALID")
    @NotNull(message = "ACCOUNT_EMAIL_INVALID")
    String email;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$",message = "ACCOUNT_PHONE_NUMBER_INVALID")
    @NotNull(message = "ACCOUNT_PHONE_NUMBER_INVALID")
    String phone_number;

    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
    Integer role_id;
}
