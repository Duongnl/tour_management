package com.tour.tour_management.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    int quantity;

    int quantity_sell;

    int quantity_reserve;

    int price;

    @NotBlank(message = "TOUR_CATEGORY_ID_NOT_BLANK")
    String category_id;

}

