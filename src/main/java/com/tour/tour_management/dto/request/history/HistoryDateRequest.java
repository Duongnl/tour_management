package com.tour.tour_management.dto.request.history;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)

public class HistoryDateRequest {
    LocalDateTime startTime;
    LocalDateTime endTime;


}
