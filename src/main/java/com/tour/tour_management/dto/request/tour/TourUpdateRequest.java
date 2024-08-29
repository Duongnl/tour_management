package com.tour.tour_management.dto.request.tour;


import com.tour.tour_management.dto.request.tourtime.TourTimeUpdateRequest;
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
public class TourUpdateRequest {

//    @NotBlank(message = "TOUR_NAME_NOT_BLANK")

    String tour_name;
    String tour_detail;

//    @Min(value = 0, message = "TOUR_QUANTITY_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
//    int quantity;
//
//    @Min(value = 0, message = "TOUR_QUANTITY_SELL_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_QUANTITY_SELL_MAX_INVALID")
//    int quantity_sell;
//
//    @Min(value = 0, message = "TOUR_QUANTITY_RESERVE_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_QUANTITY_RESERVE_MAX_INVALID")
//    int quantity_reserve;
//
//    @Min(value = 0, message = "TOUR_PRICE_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_PRICE_MAX_INVALID")
//    int price;

//    @NotBlank(message = "TOUR_CATEGORY_ID_NOT_BLANK")
    Integer category_id;

    String url;
}

