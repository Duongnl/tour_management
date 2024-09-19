package com.tour.tour_management.dto.response.reserve;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.entity.TourTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveTourResponse {


    Integer tour_id;
    String tour_name;
    String url;
    int status;

    String category_name;

    ReserveTourTimeResponse tourTime;
}
