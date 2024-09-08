package com.tour.tour_management.dto.response.tourtime;

import com.tour.tour_management.dto.response.AirlineResponse;
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
public class TourTimeDetailResponse {
    Integer tour_time_id;
    String time_name;
    LocalDateTime departure_time;
    LocalDate departure_date;
    LocalDateTime return_time;
    LocalDate return_date;
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
