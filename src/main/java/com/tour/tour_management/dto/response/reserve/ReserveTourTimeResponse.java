package com.tour.tour_management.dto.response.reserve;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tour.tour_management.dto.response.AirlineResponse;
import com.tour.tour_management.entity.Airline;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveTourTimeResponse {

    Integer tour_time_id;
    String time_name;
    int quantity_sell;
    int quantity_reserve;
    int quantity_left;
    LocalDate visa_expire;

    LocalDate departure_date;
    LocalDate return_date;

    LocalDateTime departure_time;
    LocalDateTime return_time;

    AirlineResponse departureAirline;
    AirlineResponse returnAirline;

    int price_min;
    int commission;
    int status;



    public String getDeparture_time() {
        if (departure_time != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedTime = departure_time.format(formatter);
            return formattedTime;
        } else {
            return null;
        }

    }

    public String getReturn_time() {
        if (return_time != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedTime = return_time.format(formatter);
            return formattedTime;
        } else {
            return null;
        }
    }

}