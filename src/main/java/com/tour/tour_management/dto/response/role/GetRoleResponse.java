package com.tour.tour_management.dto.response.role;


import com.tour.tour_management.entity.Permission;
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
public class GetRoleResponse {
    Integer role_id;
    String role_name;
    int status;
    Set<PermissionResponse> permissions;
}
