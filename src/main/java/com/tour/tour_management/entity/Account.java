package com.tour.tour_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String account_id;
    String account_name;
    String password;
    String email;
    String phone_number;
    ZonedDateTime time;
    int status;

    @OneToOne(mappedBy = "account")
    @JsonBackReference
    Employee employee;

    @ManyToOne
    @JoinColumn(name =  "role_id", nullable = false, referencedColumnName = "role_id")
    @JsonBackReference
    Role role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    Set<History> histories;
}
