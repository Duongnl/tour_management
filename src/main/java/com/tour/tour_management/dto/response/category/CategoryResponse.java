package com.tour.tour_management.dto.response.category;


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
public class CategoryResponse {

    Integer category_id;
    String category_name;
    String category_detail;
    String url;
    int status;

    public String getUrl () {
        return url + '-' +category_id;
    }
}


