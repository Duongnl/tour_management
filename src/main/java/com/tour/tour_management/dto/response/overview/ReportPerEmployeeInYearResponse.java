package com.tour.tour_management.dto.response.overview;

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
public class ReportPerEmployeeInYearResponse {
    private String employeeName;
    private Long[] months;
    private Long total;
    private String year;
}
