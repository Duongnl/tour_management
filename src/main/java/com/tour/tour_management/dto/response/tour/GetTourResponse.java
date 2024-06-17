package com.tour.tour_management.dto.response.tour;


import com.tour.tour_management.dto.response.TourTimeResponse;
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
public class GetTourResponse {

    String tour_id;
    String tour_name;
    String tour_detail;
    int status;
    String category_id;
    String category_name;

    Set<TourTimeResponse> tourTimes;
}
