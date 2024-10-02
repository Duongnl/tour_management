package com.tour.tour_management.dto.request.reserve;


import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveRequests {

    @Valid
    List<ReserveRequest> reserveRequests;
}
