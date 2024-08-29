package com.tour.tour_management.dto.request.tourtime;

import com.tour.tour_management.dto.request.airline.AirlineUpdateRequest;
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
public class TourTimeUpdateRequest {
    String tour_id;
    String time_name;
    LocalDateTime departure_time;
    LocalDateTime return_time;
    LocalDate visa_expire;
    int quantity;
    int quantity_reserve;
    int quantity_sell;
    int quantity_left;
    int price_min;
    int commission;
}
