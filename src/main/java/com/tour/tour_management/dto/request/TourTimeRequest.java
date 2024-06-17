package com.tour.tour_management.dto.request;

import com.tour.tour_management.dto.response.AirlineResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourTimeRequest {
    String tour_id;
    String time_name;
    Date departure_time;
    Date return_time;
    Date visa_expire;
    int quantity;
    int quantity_reserve;
    int quantity_sell;
    int quantity_left;
    int price_min;
    int commission;
    AirlineRequest departureAirline;
    AirlineRequest returnAirline;

}
