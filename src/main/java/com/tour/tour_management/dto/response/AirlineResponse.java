package com.tour.tour_management.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirlineResponse {

    String airline_id;
    String airline_name;
    String airline_detail;
    LocalDateTime departure_time;
    int status;

}
