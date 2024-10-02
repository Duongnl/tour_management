package com.tour.tour_management.dto.request.tour;


import com.tour.tour_management.dto.request.tourtime.TourTimeRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourCreateRequest {

    @Pattern(regexp = "^(?=(.*\\p{L}){2,})[\\p{L}\\p{N},() ]{2,2550}$",message ="TOUR_NAME_INVALID" )
    @NotBlank(message = "TOUR_NAME_NOT_BLANK")
    String tour_name;

    @Pattern(regexp = "^[\\p{L}0-9 ,.\\-]{0,255}",message ="TOUR_DETAIL_INVALID")
    String tour_detail;

//    @Min(value = 0, message = "TOUR_QUANTITY_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
//    int quantity;
//
//    @Min(value = 0, message = "TOUR_QUANTITY_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
//    int price;

    @NotNull
    Integer category_id;

    Set<TourTimeRequest> tourTimes;

    @Pattern(regexp = "^[a-zA-Z\\-]+$", message = "TOUR_URL_INVALID")
    String url;
}
