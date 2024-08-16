package com.tour.tour_management.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class CategoryRequest {

//    @Pattern(regexp = "^[\\p{L} ]{2,255}$",message = "CATEGORY_NAME_INVALID")
    @Pattern(regexp = "^(?=(.*\\p{L}){2,})[\\p{L} ]{2,255}$",message = "CATEGORY_NAME_INVALID")
    @NotNull(message = "CATEGORY_NAME_INVALID")
    String category_name;

//    @Pattern(regexp = "^(?=(.*\\p{L}){2,})[\\p{L} ]{2,255}$",message = "CATEGORY_DETAIL_INVALID")
    @Pattern(regexp = "^[\\p{L}0-9 ,.\\-]{0,255}$", message = "CATEGORY_DETAIL_INVALID")
    @NotNull(message = "CATEGORY_DETAIL_INVALID")
    String category_detail;

    @Pattern(regexp = "^[a-zA-Z0-9]+(?:-[a-zA-Z0-9]+)*$", message = "CATEGORY_URL_INVALID")
    @NotNull(message = "CATEGORY_URL_INVALID")
    String url;
}
