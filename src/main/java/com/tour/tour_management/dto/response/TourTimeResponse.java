package com.tour.tour_management.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    LocalDateTime departure_time;
    LocalDateTime return_time;
    LocalDate visa_expire;
    int quantity;
    int quantity_reserve;
    int quantity_sell;
    int quantity_left;
    int price_min;
    int commission;
    AirlineResponse departureAirline;
    AirlineResponse returnAirline;
    int status;

}
