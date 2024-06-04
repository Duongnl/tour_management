package com.tour.tour_management.dto.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class TourRequest {

    @NotBlank(message = "TOUR_NAME_NOT_BLANK")
    String tour_name;

    String tour_detail;

//    @Min(value = 0, message = "TOUR_QUANTITY_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
//    int quantity;
//
//    @Min(value = 0, message = "TOUR_QUANTITY_MIN_INVALID")
//    @Max(value = 2100000000, message = "TOUR_QUANTITY_MAX_INVALID")
//    int price;

    @NotBlank(message = "TOUR_CATEGORY_ID_NOT_BLANK")
    String category_id;

}
