package com.tour.tour_management.dto.response;

import com.tour.tour_management.entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// tao get set hashcode euqual,...
@Data
// tao builder
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
public class GetCategoryResponse {

    String category_id;
    String category_name;
    int status;
//    Set<CategoryMapper.TourConvert> tours;
    Set<TourResponse> tours;
}
