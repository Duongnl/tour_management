package com.tour.tour_management.dto.request;


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

    String tour_name;
    String tour_detail;
    int quantity;
    int quantity_sell;
    int quantity_reserve;
    int quantity_left;
    int price;
    int status;
    String category_id;

}
