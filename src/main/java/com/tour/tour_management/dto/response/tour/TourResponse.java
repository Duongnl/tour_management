package com.tour.tour_management.dto.response.tour;


import lombok.*;
import lombok.experimental.FieldDefaults;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourResponse {
    Integer tour_id;
    String tour_name;
    String tour_detail;
    int status;
    Integer category_id;
    String category_name;
    String url;
}
