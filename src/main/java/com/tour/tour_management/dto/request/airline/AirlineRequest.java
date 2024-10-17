package com.tour.tour_management.dto.request.airline;

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
public class AirlineRequest {

    @Pattern(regexp = "^(?!\\s*$)[\\p{L}0-9 ,\\.\\-]{0,255}$", message = "AIRLINE_DETAIL_INVALID")
    @NotNull(message = "AIRLINE_NAME_INVALID")
    String airline_name;

    @Pattern(regexp = "^[\\p{L}0-9 ,.\\-]{0,255}$", message = "AIRLINE_DETAIL_INVALID")
    @NotNull(message = "AIRLINE_DETAIL_INVALID")
    String airline_detail;

//    @NotBlank(message = "AIRLINE_DEPARTURE_NOT_BLANK")
//    @NotBlank(message = "AIRLINE_RETURN_NOT_BLANK")

}
