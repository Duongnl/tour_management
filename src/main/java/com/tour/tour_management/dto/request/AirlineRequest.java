package com.tour.tour_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirlineRequest {
//    @NotBlank (message = "AIRLINE_NAME_NOT_BLANK")
    String airline_name;

    String airline_detail;

//    @NotBlank(message = "AIRLINE_DEPARTURE_NOT_BLANK")
ZonedDateTime departure_time;
//    @NotBlank(message = "AIRLINE_RETURN_NOT_BLANK")

}
