package com.tour.tour_management.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class TourUpdateRequest {

    @NotBlank(message = "TOUR_NAME_NOT_BLANK")
    String tour_name;

    String tour_detail;

    @Min(value = 0, message = "TOUR_QUANTITY_INVALID")
    int quantity;

    @Min(value = 0, message = "TOUR_QUANTITY_SELL_INVALID")
    int quantity_sell;

    @Min(value = 0, message = "TOUR_QUANTITY_RESERVE_INVALID")
    int quantity_reserve;

    @Min(value = 0, message = "TOUR_PRICE_INVALID")
    int price;

    @NotBlank(message = "TOUR_CATEGORY_ID_NOT_BLANK")
    String category_id;

}

