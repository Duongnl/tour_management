package com.tour.tour_management.dto.request.category;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {

    @NotBlank(message = "CATEGORY_NAME_NOT_BLANK")
    String category_name;
    String category_detail;
    @NotBlank(message = "CATEGORY_URL_NOT_BLANK")
    String url;
}
