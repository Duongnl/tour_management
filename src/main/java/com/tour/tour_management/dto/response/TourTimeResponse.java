package com.tour.tour_management.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourTimeResponse {
    String tour_time_id;
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
    AirlineResponse departureAirline;
    AirlineResponse returnAirline;

}
