package com.tour.tour_management.dto.request.role;

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
public class RoleRequest {
    @Pattern(regexp = "^(?=(.*\\p{L}){2,})[\\p{L} ]{2,255}",message = "ROLE_NAME_INVALID")
    @NotNull(message = "ROLE_NAME_INVALID")
    String role_name;

    String[] permission;


}
