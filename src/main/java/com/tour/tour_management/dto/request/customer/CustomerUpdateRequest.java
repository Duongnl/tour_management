package com.tour.tour_management.dto.request.customer;

import jakarta.validation.constraints.NotBlank;

public class CustomerUpdateRequest {
    @NotBlank(message = "CUSTOMER_NAME_NOT_BLANK")
    String customer_name;
    int status;
}
