package com.tour.tour_management.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
//tao get set
@Getter
@Setter
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String permission_id;
    String permission_name;
    int status;

    @ManyToMany(mappedBy = "permissions")
    @JsonBackReference
    Set<Role> roles;
}


