package com.tour.tour_management.dto.request.tourtime;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalDate;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourTimeRequest {

    @NotBlank(message = "TIME_NAME_NOT_BLANK")
    @Pattern(regexp = "^(?=(.*\\p{L}){2,})[\\p{L} ]{2,255}",message ="TIME_NAME_INVALID" )
    String time_name;

    @NotNull(message = "TIME_DEPARTURE_TIME_INVALID")
    LocalDateTime departure_time;

    @NotNull(message = "TIME_RETURN_TIME_INVALID")
    LocalDateTime return_time;

    @NotNull(message = "TIME_DEPARTURE_DATE_INVALID")
    LocalDate departure_date;

    @NotNull(message = "TIME_RETURN_DATE_INVALID")
    LocalDate return_date;

    @NotNull(message = "TIME_VISA_EXPIRE_INVALID")
    LocalDate visa_expire;

    @Min(value = 0, message = "TIME_QUANTITY_MIN_INVALID")
    @Max(value = 2100000000, message = "TIME_QUANTITY_MAX_INVALID")
    int quantity;

    @Min(value = 0, message = "TIME_QUANTITY_RESERVE_MIN_INVALID")
    @Max(value = 2100000000, message = "TIME_QUANTITY_RESERVE_MAX_INVALID")
    int quantity_reserve;

    @Min(value = 0, message = "TIME_QUANTITY_SELL_MIN_INVALID")
    @Max(value = 2100000000, message = "TIME_QUANTITY_SELL_MAX_INVALID")
    int quantity_sell;

    @Min(value = 0, message = "TIME_QUANTITY_MIN_INVALID")
    @Max(value = 2100000000, message = "TIME_QUANTITY_MAX_INVALID")
    int quantity_left;

    @Min(value = 0, message = "TIME_PRICE_MIN_INVALID")
    @Max(value = 2100000000, message = "TIME_PRICE_MAX_INVALID")
    int price_min;

    @Min(value = 0, message = "TIME_QUANTITY_MIN_INVALID")
    @Max(value = 2100000000, message = "TIME_QUANTITY_MAX_INVALID")
    int commission;

    Integer departure_airline_id;
    Integer return_airline_id;
}
