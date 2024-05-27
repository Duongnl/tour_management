package com.tour.tour_management.dto.response;


import com.tour.tour_management.entity.Tour;
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
public class CategoryResponse {

    String category_id;
    String category_name;
    int status;
    Set<Tour> tours;

}
