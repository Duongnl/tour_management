package com.tour.tour_management.dto.request.reserve;


import com.tour.tour_management.dto.response.customer.CustomerResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveRequest {
    CustomerResponse customerResponse;

//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
    Integer tour_time_id;

//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
    Integer employee_id;;

    @Pattern(regexp = "^[\\p{L}0-9 ,.\\-]{0,255}$", message = "RESERVE_NOT_INVALID")
    @NotNull(message = "RESERVE_NOT_INVALID")
    String note;

//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
    Integer price_min;

//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
    Integer price;

//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
    Integer pay;

//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
    Integer commission;

}
