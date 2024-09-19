package com.tour.tour_management.dto.request.reserve;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveTourFilterRequest {

    LocalDate start_date;

    LocalDate end_date;

    Boolean date_filter;

    @Pattern(regexp = "^[a-zA-Z0-9]+(?:-[a-zA-Z0-9]+)*$", message = "CATEGORY_URL_INVALID")
    String category_slug;

}
